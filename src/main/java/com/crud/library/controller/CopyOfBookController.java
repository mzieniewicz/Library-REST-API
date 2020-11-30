package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.CopyOfBook;
import com.crud.library.domain.CopyOfBookDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class CopyOfBookController {

    @RequestMapping(method = RequestMethod.GET, value = "/copyOfBooks")
    public List<BookDto> getCopyOfBooks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/copyOfBooks/{copyOfBookId}")
    public CopyOfBookDto getCopyOfBooks(@RequestParam Long copyOfBookId) {
        return new CopyOfBookDto();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/copyOfBooks/{copyOfBookId}")
    public void deleteCopyOfBooks(@RequestParam Long copyOfBookId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/copyOfBooks")
    public BookDto updateCopyOfBook(@RequestBody CopyOfBook copyOfBook) {
        return new BookDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/copyOfBooks", consumes = APPLICATION_JSON_VALUE)
    public void createCopyOfBook(@RequestBody CopyOfBook copyOfBook) {
    }
}
