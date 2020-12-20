package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.facade.SearchException;
import com.crud.library.facade.SearchingFacade;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private SearchingFacade searchingFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(service.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookId}")
    public BookDto getBookByBookId(@PathVariable Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(service.getBook(bookId).orElseThrow(BookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/getByTitle/{title}")
    public List<BookDto> getBookTitle(@PathVariable String title) throws SearchException {
        return searchingFacade.searchBookTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/getByAuthor/{author}")
    public List<BookDto> getBookAuthor(@PathVariable String author) throws SearchException {
        return searchingFacade.searchBookAuthor(author);
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
