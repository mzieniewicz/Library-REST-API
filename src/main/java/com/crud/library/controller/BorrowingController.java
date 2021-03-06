package com.crud.library.controller;

import com.crud.library.domain.BorrowingDto;
import com.crud.library.mapper.BorrowingMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BorrowingController {
    @Autowired
    private DbService service;

    @Autowired
    private BorrowingMapper borrowingMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/borrowings")
    public List<BorrowingDto> getBorrowings() {
        return borrowingMapper.mapToBorrowingDtoList(service.getBorrowings());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/borrowings/{borrowingId}")
    public BorrowingDto getBorrowing(@PathVariable Long borrowingId) throws BorrowingNotFoundException {
        return borrowingMapper.mapToBorrowingDto(service.getBorrowing(borrowingId).orElseThrow(BorrowingNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/borrowings/{borrowingId}")
    public void deleteBorrowing(@PathVariable Long borrowingId) {
        service.deleteBorrowing(borrowingId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/borrowings")
    public BorrowingDto updateBorrowing(@RequestBody BorrowingDto borrowingDto) {
        return borrowingMapper.mapToBorrowingDto(service.saveBorrowing(borrowingMapper.mapToBorrowing(borrowingDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/borrowings", consumes = APPLICATION_JSON_VALUE)
    public void createBorrowing(@RequestBody BorrowingDto borrowingDto) {
        service.saveBorrowing(borrowingMapper.mapToBorrowing(borrowingDto));
    }
}

