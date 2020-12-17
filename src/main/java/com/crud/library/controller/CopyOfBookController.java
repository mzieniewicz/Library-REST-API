package com.crud.library.controller;

import com.crud.library.domain.CopyOfBookDto;
import com.crud.library.mapper.CopyMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class CopyOfBookController {

    @Autowired
    private DbService service;

    @Autowired
    private CopyMapper copyMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/copyOfBooks")
    public List<CopyOfBookDto> getCopiesOfBook() {
        return copyMapper.mapToCopyOfBookDtoList(service.getAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copyOfBooks/{copyOfBookId}")
    public CopyOfBookDto getCopyOfBooks(@RequestParam Long copyOfBookId) throws CopyNotFoundException {
        return copyMapper.mapToCopyOfBookDto(service.getCopyById(copyOfBookId).orElseThrow(CopyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/copyOfBooks/{copyOfBookId}")
    public void deleteCopyOfBooks(@RequestParam Long copyOfBookId) {
        service.deleteCopy(copyOfBookId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/copyOfBooks")
    public CopyOfBookDto updateCopyOfBook(@RequestBody CopyOfBookDto copyOfBookDto) {
        return copyMapper.mapToCopyOfBookDto(service.saveCopy(copyMapper.mapToCopyOfBook(copyOfBookDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/copyOfBooks", consumes = APPLICATION_JSON_VALUE)
    public void createCopyOfBook(@RequestBody CopyOfBookDto copyOfBookDto) {
        service.saveCopy(copyMapper.mapToCopyOfBook(copyOfBookDto));
    }
}
