package com.crud.library.controller;


import com.crud.library.domain.BookDto;

import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public BookDto getBookByBookId(@PathVariable Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(service.getBook(bookId).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/getByTitle/{title}")
    public List<BookDto> getBookTitle(@PathVariable String title) {
        List<BookDto> bookDtos = bookMapper.mapToBookDtoList(service.getAllBooks()).stream()
                .filter(b -> b.getTitle().contains(title))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/getByAuthor/{author}")
    public List<BookDto> getBookAuthor(@PathVariable String author) {
        List<BookDto> bookDtos = bookMapper.mapToBookDtoList(service.getAllBooks()).stream()
                .filter(b -> b.getAuthor().contains(author))
                .collect(Collectors.toList());
        return bookDtos;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        service.deleteBook(bookId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/books")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(service.saveBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = APPLICATION_JSON_VALUE)
    public void createBook(@RequestBody BookDto bookDto) {
        service.saveBook(bookMapper.mapToBook(bookDto));
    }
}
