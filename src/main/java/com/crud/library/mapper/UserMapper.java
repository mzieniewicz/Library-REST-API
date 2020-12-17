package com.crud.library.mapper;

import com.crud.library.domain.User;
import com.crud.library.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getUserSurname(),
                userDto.getPESEL(),
                userDto.getUserEmail(),
                userDto.getAccountCreationDate(),
                userDto.getAccountBalance(),
                userDto.getBorrowings());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getUserName(),
                user.getUserSurname(),
                user.getPesel(),
                user.getUserEmail(),
                user.getAccountCreationDate(),
                user.getAccountBalance(),
                user.getBorrowings());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(u -> new UserDto(u.getUserId(), u.getUserName(), u.getUserSurname(), u.getPesel(),
                        u.getUserEmail(), u.getAccountCreationDate(), u.getAccountBalance(), u.getBorrowings()))
                .collect(Collectors.toList());
    }

}
