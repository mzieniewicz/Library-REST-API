package com.crud.library.controller;


import com.crud.library.domain.BookDto;
import com.crud.library.domain.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BookController {

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public List<BookDto> getBooks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookId}")
    public BookDto getBook(@RequestParam Long bookId) {
        return new BookDto();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{bookId}")
    public void deleteBook(@RequestParam Long bookId) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/books")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return new BookDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
    }
}
