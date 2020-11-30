package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.BorrowingDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BorrowingController {

    @RequestMapping(method = RequestMethod.GET, value = "/borrowings")
    public List<BorrowingDto> getBorrowings() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/borrowings/{borrowingId}")
    public BorrowingDto getBorrowing(@RequestParam Long borrowingId) {
        return new BorrowingDto();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/borrowings/{borrowingId}")
    public void deleteBorrowing(@RequestParam Long borrowingId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/borrowings")
    public BorrowingDto updateBorrowing(@RequestBody BorrowingDto borrowingDto) {
        return new BorrowingDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/borrowings", consumes = APPLICATION_JSON_VALUE)
    public void createBorrowing(@RequestBody BorrowingDto borrowingDto) {
    }
}

