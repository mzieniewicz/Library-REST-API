package com.crud.library.facade;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.UserDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.UserMapper;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.UserRepository;
import com.crud.library.service.DbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/books")
public class SearchingFacade {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private DbService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchingFacade.class);

    @RequestMapping(method = RequestMethod.GET, value = "/books/getByTitle/{title}")
    public List<BookDto> searchBookTitle(String title) throws SearchException {
        LOGGER.info("The search for the book's title with the letters: " + title + "has started");
        List<BookDto> bookDtos = bookMapper.mapToBookDtoList(service.getAllBooks()).stream()
                .filter(b -> b.getTitle().contains(title))
                .collect(Collectors.toList());
        if (bookDtos.isEmpty()) {
            LOGGER.error(SearchException.EER_NOT_FOUND);
            throw new SearchException(SearchException.EER_NOT_FOUND);
        }
        return bookDtos;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/getByAuthor/{author}")
    public List<BookDto> searchBookAuthor(String author) throws SearchException {
        LOGGER.info("The search for the book's author with the letters: " + author + "has started");
        List<BookDto> bookDtos = bookMapper.mapToBookDtoList(service.getAllBooks()).stream()
                .filter(b -> b.getAuthor().contains(author))
                .collect(Collectors.toList());
        if (bookDtos.isEmpty()) {
            LOGGER.error(SearchException.EER_NOT_FOUND);
            throw new SearchException(SearchException.EER_NOT_FOUND);
        }
        return bookDtos;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/getBySurname/{userSurname}")
    public List<UserDto> searchUsersBySurname(String userSurname) throws SearchException {
        LOGGER.info("The search for an user with a surname with the letters: " + userSurname + " has started");
        List<UserDto> userDtos = userMapper.mapToUserDtoList(service.getUsers()).stream()
                .filter(u -> u.getUserSurname().contains(userSurname))
                .collect(Collectors.toList());
        if (userDtos.isEmpty()) {
            LOGGER.error(SearchException.EER_NOT_FOUND);
            throw new SearchException(SearchException.EER_NOT_FOUND);
        }
        return userDtos;
    }
}
