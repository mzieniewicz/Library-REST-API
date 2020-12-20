package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.CopyOfBook;
import com.crud.library.facade.SearchingFacade;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.Matchers.is;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private BookMapper bookMapper;

    @MockBean
    private SearchingFacade searchingFacade;

    @Test
    void getEmptyBooksTest() throws Exception {
        //Given
        List<BookDto> bookDtos = new ArrayList<>();
        when(bookMapper.mapToBookDtoList(service.getAllBooks())).thenReturn(bookDtos);

        //When & Then
        mockMvc.perform(get("/v1/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getBooksTest() throws Exception {
        //Given
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto());
        bookDtos.add(new BookDto(1L, "title1", "author1", 1999, new ArrayList<>()));

        when(bookMapper.mapToBookDtoList(service.getAllBooks())).thenReturn(bookDtos);

        //When & Then
        mockMvc.perform(get("/v1/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].bookId", is(1)))
                .andExpect(jsonPath("$[1].title", is("title1")))
                .andExpect(jsonPath("$[1].author", is("author1")))
                .andExpect(jsonPath("$[1].yearOfPublication", is(1999)))
                .andExpect(jsonPath("$[1].copies", is(new ArrayList<>())));
    }

    @Test
    void getBookBybookIdTest() throws Exception {
        //Given
        List<CopyOfBook> copies = new ArrayList<>();
        copies.add(new CopyOfBook());
        BookDto bookDto = new BookDto(1L, "title1", "author1", 1999, copies);
        Book book = new Book(1L, "title1", "author1", 1999, copies);
        Long bookId = 1L;
        when(bookMapper.mapToBookDto(book)).thenReturn(bookDto);
        when(service.getBook(bookId)).thenReturn(Optional.of(book));

        //When & Then
        mockMvc.perform(get("/v1/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId", is(1)))
                .andExpect(jsonPath("$.title", is("title1")))
                .andExpect(jsonPath("$.author", is("author1")))
                .andExpect(jsonPath("$.yearOfPublication", is(1999)))
                .andExpect(jsonPath("$.copies[0].copyId", is(0)));
    }

    @Test
    void getBookByTitleTest() throws Exception {
        //Given
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto(1L, "title1", "author1", 1999, new ArrayList<>()));
        String title = "title1";

        when(searchingFacade.searchBookTitle(title)).thenReturn(bookDtos);

        //When & Then
        mockMvc.perform(get("/v1/books/getByTitle/title1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("title", "title1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookId", is(1)))
                .andExpect(jsonPath("$[0].title", is("title1")))
                .andExpect(jsonPath("$[0].author", is("author1")))
                .andExpect(jsonPath("$[0].yearOfPublication", is(1999)));

    }

    @Test
    void getBookByAuthorTest() throws Exception {
        //Given
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto(2L, "title2", "author2", 2011, new ArrayList<>()));
        String author = "author2";

        when(searchingFacade.searchBookAuthor(author)).thenReturn(bookDtos);

        //When & Then
        mockMvc.perform(get("/v1/books/getByAuthor/author2")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("author", "author2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookId", is(2)))
                .andExpect(jsonPath("$[0].title", is("title2")))
                .andExpect(jsonPath("$[0].author", is("author2")))
                .andExpect(jsonPath("$[0].yearOfPublication", is(2011)));
    }

    @Test
    public void deleteNoBookTest() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/books/11").contentType(MediaType.APPLICATION_JSON)
                .param("bookId", "11"))
                .andExpect(status().is(200));
    }

    @Test
    public void updateBookTest() throws Exception {
        //Given
        Book book = new Book(1L, "updated title1", " updated author1", 2020, new ArrayList<>());
        BookDto bookDto = new BookDto(1L, "updated title1", "updated author1", 2020, new ArrayList<>());

        when(bookMapper.mapToBook(ArgumentMatchers.any(BookDto.class))).thenReturn(book);
        when(service.saveBook(book)).thenReturn(book);
        when(bookMapper.mapToBookDto(book)).thenReturn(bookDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

        //When & Then
        mockMvc.perform(put("/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("bookId", "1")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.bookId", is(1)))
                .andExpect(jsonPath("$.title", is("updated title1")))
                .andExpect(jsonPath("$.author", is("updated author1")));
    }

    @Test
    public void createBookTest() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1L, "title1", "author1", 1999, new ArrayList<>());
        Book book = new Book(1L, "title1", "author1", 1999, new ArrayList<>());
        Long bookId = 1L;

        when(bookMapper.mapToBook(ArgumentMatchers.any(BookDto.class))).thenReturn(book);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

        //When & Then
        mockMvc.perform(post("/v1/books")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(("UTF-8"))
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}