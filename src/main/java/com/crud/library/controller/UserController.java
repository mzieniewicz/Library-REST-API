package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.UserDto;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private DbService service;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(service.getUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUser(@PathVariable Long userId) throws UserNotFoundException{
        return userMapper.mapToUserDto(service.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/getBySurname/{userSurname}")
    public List<UserDto> getUsersBySurname(@PathVariable String userSurname) {
        List<UserDto> userDtos = userMapper.mapToUserDtoList(service.getUsers()).stream()
                .filter(u -> u.getUserSurname().contains(userSurname))
                .collect(Collectors.toList());
        return userDtos;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        service.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(service.saveUser(userMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        service.deleteUser(userId);
    }

}
