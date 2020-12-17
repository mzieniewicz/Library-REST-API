package com.crud.library.mapper;

import com.crud.library.domain.Borrowing;
import com.crud.library.domain.CopyOfBook;
import com.crud.library.domain.User;
import com.crud.library.domain.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    public  void mapToUserTest(){
        //Given
        CopyOfBook copy = new CopyOfBook(11L, true);
        List<Borrowing> borrowings = new ArrayList<>();
        borrowings.add(new Borrowing(1L, copy, new User(), LocalDate.now(), LocalDate.now().plusDays(30L)));
        UserDto userDto = new UserDto(1L,"Tom", "New",1234,"tomnew@test.com", LocalDate.now().minusDays(15),BigDecimal.ONE,borrowings);
        //When
        User resultUser = userMapper.mapToUser(userDto);
        //Then
        assertEquals(1L, resultUser.getUserId());
        assertEquals("tomnew@test.com", resultUser.getUserEmail());
        assertEquals(LocalDate.now().minusDays(15), resultUser.getAccountCreationDate());
        assertEquals(BigDecimal.ONE, resultUser.getAccountBalance());
        assertEquals(1, resultUser.getBorrowings().size());
        assertEquals(11L, resultUser.getBorrowings().get(0).getCopyOfBook().getCopyId());
        assertEquals(LocalDate.now().plusDays(30L), resultUser.getBorrowings().get(0).getReturnOfTheCopyDate());
    }
    @Test
    public  void mapToUserDtoTest(){
        //Given
        User user = new User(2L,"Maria", "Twin",456,"maria.t@test.com");
        //When
        UserDto resultUserDto = userMapper.mapToUserDto(user);
        //Then
        assertEquals(2L, resultUserDto.getUserId());
        assertEquals("Maria", resultUserDto.getUserName());
        assertEquals("Twin", resultUserDto.getUserSurname());
        assertEquals(456, resultUserDto.getPESEL());
        assertEquals("maria.t@test.com", resultUserDto.getUserEmail());
    }
    @Test
    public  void mapToUserDtoListTest(){
        //Given
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        //When
        List<UserDto> resultUserDtos = userMapper.mapToUserDtoList(users);
        //Then
        assertNotNull(resultUserDtos);
        assertEquals(3, resultUserDtos.size());
    }
}
