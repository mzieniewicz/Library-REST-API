package com.crud.library.facade;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.UserDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchingFacadeTest {

    @InjectMocks
    private SearchingFacade searchingFacade;
    @Mock
    private UserMapper userMapper;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private DbService service;

    @Test
    public void  searchBookTitleTest() throws SearchException{
        //Given
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto(1L, "title1", "author1", 1999, new ArrayList<>()));
        bookDtos.add(new BookDto(2L, "title1", "author2", 2010, new ArrayList<>()));
        String title ="title1";
        when(bookMapper.mapToBookDtoList(service.getBookTitle(title))).thenReturn(bookDtos);
        //When
        List<BookDto> resultList = searchingFacade.searchBookTitle(title);
        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }

    @Test
    public void  searchBookAuthorTest() throws SearchException{
        //Given
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(new BookDto(1L, "title1", "author1", 1999, new ArrayList<>()));
        bookDtos.add(new BookDto(2L, "title2", "author1", 2010, new ArrayList<>()));
        bookDtos.add(new BookDto(3L, "title3", "author1", 2011, new ArrayList<>()));
        String author ="author1";
        when(bookMapper.mapToBookDtoList(service.getBookAuthor(author))).thenReturn(bookDtos);
        //When
        List<BookDto> resultList = searchingFacade.searchBookAuthor(author);
        //Then
        assertNotNull(resultList);
        assertEquals(3, resultList.size());
    }

    @Test
    public void  searchUsersBySurnameTest() throws SearchException{
        //Given
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1L, "name", "surname1", 1234, "user@test.com" ));
        userDtos.add(new UserDto(2L, "name2", "surname1", 3225, "user2@test.com"));
        String userSurname = "surname1";
        when(userMapper.mapToUserDtoList(service.getUsersBySurname(userSurname))).thenReturn(userDtos);
        //When
        List<UserDto> resultList = searchingFacade.searchUsersBySurname(userSurname);
        //Then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
    }



}