package com.crud.library.controller;


import com.crud.library.domain.BookDto;
import com.crud.library.domain.UserDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private DbService service;

    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(service.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookId}")
    public BookDto getBook(@PathVariable Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(service.getBook(bookId).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{title}")
    public BookDto getBookTitle(@RequestParam String title) throws BookNotFoundException {
        return bookMapper.mapToBookDto(service.getBookTitle(title).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{author}")
    public BookDto getBookAuthor(@RequestParam String author) throws BookNotFoundException {
        return bookMapper.mapToBookDto(service.getBookAuthor(author).orElseThrow(BookNotFoundException::new));
    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{bookId}")
//    public void deleteBook(@PathVariable Long bookId) {
//        service.delete(bookId);
//    }

//    @RequestMapping(method = RequestMethod.PUT, value = "/books")
//    public BookDto updateBook(@RequestBody BookDto bookDto) {
//        return new BookDto();
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = APPLICATION_JSON_VALUE)
//    public void createBook(@RequestBody BookDto bookDto) {
//    }
}
