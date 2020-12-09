package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.UserDto;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private DbService service;

//    @Autowired
//    private
//
//    @RequestMapping(method = RequestMethod.GET, value = "/user")
//    public List<UserDto> getUsers() {
//        return use.mapToBookDtoList(service.getAllBooks());
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUser() {
        return new UserDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return new UserDto();
    }
//blockUser,  generateToken

}
