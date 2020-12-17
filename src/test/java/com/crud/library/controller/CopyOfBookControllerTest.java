package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.CopyOfBook;
import com.crud.library.domain.CopyOfBookDto;
import com.crud.library.mapper.CopyMapper;
import com.crud.library.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CopyOfBookController.class)
public class CopyOfBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private CopyMapper copyMapper;

    @Test
    void getEmptyCopiesOfBookTest() throws Exception {
        //Given
        List<CopyOfBookDto> copyOfBookDtos = new ArrayList<>();
        when(copyMapper.mapToCopyOfBookDtoList(service.getAllCopies())).thenReturn(copyOfBookDtos);

        //When & Then
        mockMvc.perform(get("/v1/copyOfBooks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getCopiesOfBookTest() throws Exception {
        //Given
        Book book = new Book(1L, "title1", "author1", 1999, new ArrayList<>());
        List<CopyOfBookDto> copyOfBookDtos = new ArrayList<>();
        copyOfBookDtos.add(new CopyOfBookDto());
        copyOfBookDtos.add(new CopyOfBookDto(22L, true, book));
        when(copyMapper.mapToCopyOfBookDtoList(service.getAllCopies())).thenReturn(copyOfBookDtos);

        //When & Then
        mockMvc.perform(get("/v1/copyOfBooks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].copyId", is(0)))
                .andExpect(jsonPath("$[1].copyId", is(22)))
                .andExpect(jsonPath("$[1].eligible", is(true)))
                .andExpect(jsonPath("$[1].book.bookId", is(1)))
                .andExpect(jsonPath("$[1].book.yearOfPublication", is(1999)))
                .andExpect(jsonPath("$[1].book.copies", is(new ArrayList<>())));
    }

    @Test
    void getCopyOfBooksTest() throws Exception {
        //Given
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto( 33L, true);
        CopyOfBook copy = new CopyOfBook(33L, true);
        Long copyOfBookId = 33L;
        when(copyMapper.mapToCopyOfBookDto(copy)).thenReturn(copyOfBookDto);
        when(service.getCopyById(copyOfBookId)).thenReturn(Optional.of(copy));

        //When & Then
        mockMvc.perform(get("/v1/copyOfBooks/33")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("copyOfBookId", "33"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId", is(33)))
                .andExpect(jsonPath("$.eligible", is(true)));
    }

    @Test
    public void deleteNoCopyOfBookTest() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/copyOfBooks/11").contentType(MediaType.APPLICATION_JSON)
                .param("copyOfBookId", "11"))
                .andExpect(status().is(200));
    }

    @Test
    public void updateCopyOfBookTest() throws Exception {
        //Given
        Book book = new Book(1L, "title1", "author1", 1999, new ArrayList<>());
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto( 102L, false, book);
        CopyOfBook copy = new CopyOfBook(102L, false, book);

        when(copyMapper.mapToCopyOfBook(ArgumentMatchers.any(CopyOfBookDto.class))).thenReturn(copy);
        when(service.saveCopy(copy)).thenReturn(copy);
        when(copyMapper.mapToCopyOfBookDto(copy)).thenReturn(copyOfBookDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(copyOfBookDto);

        //When & Then
        mockMvc.perform(put("/v1/copyOfBooks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("copyId", "102")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.copyId", is(102)))
                .andExpect(jsonPath("$.eligible", is(false)))
                .andExpect(jsonPath("$.book.title", is("title1")))
                .andExpect(jsonPath("$.book.author", is("author1")));
    }
    @Test
    public void createCopyOfBookTest() throws Exception {
        //Given
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto( 10L, false);
        CopyOfBook copy = new CopyOfBook(10L, false);

        when(copyMapper.mapToCopyOfBook(ArgumentMatchers.any(CopyOfBookDto.class))).thenReturn(copy);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(copyOfBookDto);

        //When & Then
        mockMvc.perform(post("/v1/copyOfBooks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(("UTF-8"))
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}
