package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.facade.SearchingFacade;
import com.crud.library.mapper.UserMapper;
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

import java.math.BigDecimal;
import java.time.LocalDate;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private SearchingFacade searchingFacade;

    @Test
    void getEmptyUsersTest() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        when(userMapper.mapToUserDtoList(service.getUsers())).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getUsersTest() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto());
        userDtos.add(new UserDto(1L, "name", "surname1", 1234, "user@test.com", LocalDate.now(), BigDecimal.TEN, new ArrayList<>()));
        userDtos.add(new UserDto());
        when(userMapper.mapToUserDtoList(service.getUsers())).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].userId", is(1)))
                .andExpect(jsonPath("$[1].userName", is("name")))
                .andExpect(jsonPath("$[1].userSurname", is("surname1")))
                .andExpect(jsonPath("$[1].pesel", is(1234)))
                .andExpect(jsonPath("$[1].userEmail", is("user@test.com")))
                .andExpect(jsonPath("$[1].accountBalance", is(10)))
                .andExpect(jsonPath("$[1].borrowings", hasSize(0)));
    }

    @Test
    void getUserByIdTest() throws Exception {
        //Given
        UserDto userDto = new UserDto(13L, "name", "surname", 1234, "user@test.com");
        User user = new User(13L, "name", "surname", 1234, "user@test.com");
        Long userId = 13L;
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(service.getUser(userId)).thenReturn(Optional.of(user));

        //When & Then
        mockMvc.perform(get("/v1/users/13")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("userId", "13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(13)))
                .andExpect(jsonPath("$.userName", is("name")))
                .andExpect(jsonPath("$.userSurname", is("surname")))
                .andExpect(jsonPath("$.pesel", is(1234)))
                .andExpect(jsonPath("$.userEmail", is("user@test.com")));
    }

    @Test
    void getUsersBySurnameTest() throws Exception {
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(12L, "name12", "surname12", 888, "user@test.com"));
        String userSurname = "surname12";

        when(searchingFacade.searchUsersBySurname(userSurname)).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get("/v1/users/getBySurname/surname12")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("userSurname", "surname12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(12)))
                .andExpect(jsonPath("$[0].userName", is("name12")))
                .andExpect(jsonPath("$[0].userSurname", is("surname12")))
                .andExpect(jsonPath("$[0].pesel", is(888)))
                .andExpect(jsonPath("$[0].userEmail", is("user@test.com")));
    }

    @Test
    public void deleteNoUserTest() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/users/111").contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "111"))
                .andExpect(status().is(200));
    }

    @Test
    public void updateEmptyUserTest() throws Exception {
        //Given
        User user = new User();
        UserDto userDto = new UserDto();

        when(userMapper.mapToUser(ArgumentMatchers.any(UserDto.class))).thenReturn(user);
        when(service.saveUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("userId", "0")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", is(0)));

    }

    @Test
    public void createUserTest() throws Exception {
        //Given
        User user = new User();
        UserDto userDto = new UserDto();

        when(userMapper.mapToUser(ArgumentMatchers.any(UserDto.class))).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(("UTF-8"))
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}
