package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.mapper.BorrowingMapper;
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
@WebMvcTest(BorrowingController.class)
public class BorrowingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private BorrowingMapper borrowingMapper;

    @Test
    void getEmptyBorrowingsTest() throws Exception {
        //Given
        List<BorrowingDto> borrowingDtos = new ArrayList<>();
        when(borrowingMapper.mapToBorrowingDtoList(service.getBorrowings())).thenReturn(borrowingDtos);

        //When & Then
        mockMvc.perform(get("/v1/borrowings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getBorrowingsTest() throws Exception {
        //Given
        User user = new User(13L, "name", "surname", 1234,"user@test.com");
        CopyOfBook copy = new CopyOfBook(2L, false, new Book());
        List<BorrowingDto> borrowingDtos = new ArrayList<>();
        borrowingDtos.add(new BorrowingDto());
        borrowingDtos.add(new BorrowingDto(1L,copy,user));
        when(borrowingMapper.mapToBorrowingDtoList(service.getBorrowings())).thenReturn(borrowingDtos);

        //When & Then
        mockMvc.perform(get("/v1/borrowings").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].borrowingId", is(1)))
                .andExpect(jsonPath("$[1].copyOfBook.copyId", is(2)))
                .andExpect(jsonPath("$[1].user.userName", is("name")));
    }

    @Test
    void getBorrowingByIdTest() throws Exception {
        //Given
        User user = new User(13L, "name", "surname", 1234,"user@test.com");
        CopyOfBook copy = new CopyOfBook(2L, false, new Book());
        Borrowing borrowing = new Borrowing(12L, copy,user);
        BorrowingDto borrowingDto = new BorrowingDto(12L, copy,user);
        Long borrowingId = 12L;
        when(borrowingMapper.mapToBorrowingDto(borrowing)).thenReturn(borrowingDto);
        when(service.getBorrowing(borrowingId)).thenReturn(Optional.of(borrowing));

        //When & Then
        mockMvc.perform(get("/v1/borrowings/12")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("borrowingId", "12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.borrowingId", is(12)))
                .andExpect(jsonPath("$.copyOfBook.eligible", is(false)))
                .andExpect(jsonPath("$.user.pesel", is(1234)));
    }

    @Test
    public void deleteNoBorrowingTest() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/borrowings/7").contentType(MediaType.APPLICATION_JSON)
                .param("borrowingId", "7"))
                .andExpect(status().is(200));
    }

    @Test
    public void updateBorrowingTest() throws Exception {
        //Given
        Borrowing borrowing = new Borrowing();
        BorrowingDto borrowingDto = new BorrowingDto();

        when(borrowingMapper.mapToBorrowing(ArgumentMatchers.any(BorrowingDto.class))).thenReturn(borrowing);
        when(service.saveBorrowing(borrowing)).thenReturn(borrowing);
        when(borrowingMapper.mapToBorrowingDto(borrowing)).thenReturn(borrowingDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(borrowingDto);

        //When & Then
        mockMvc.perform(put("/v1/borrowings")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("borrowingId", "0")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.borrowingId", is(0)));
    }

    @Test
    public void createBorrowingTest() throws Exception {
        //Given
        Borrowing borrowing = new Borrowing();
        BorrowingDto borrowingDto = new BorrowingDto();
        Long bookId = 0L;

        when(borrowingMapper.mapToBorrowing(ArgumentMatchers.any(BorrowingDto.class))).thenReturn(borrowing);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(borrowingDto);

        //When & Then
        mockMvc.perform(post("/v1/borrowings")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(("UTF-8"))
                .content(jsonContent))
                .andExpect(status().isOk());
    }




}
