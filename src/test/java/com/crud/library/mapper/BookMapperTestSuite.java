package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.CopyOfBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookMapperTestSuite {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void mapToBookTest() {
        //Given
        List<CopyOfBook> copies = new ArrayList<>();
        BookDto book1 = new BookDto(1L, "title", "author", 1999, copies);
        //When
        Book resultBook = bookMapper.mapToBook(book1);
        //Then
        assertEquals(1L, resultBook.getBookId(), 0.01);
        assertEquals("title", resultBook.getTitle());
        assertEquals("author", resultBook.getAuthor());
    }

    @Test
    public void mapToTaskDtoTest() {
        List<CopyOfBook> copies = new ArrayList<>();
        Book book1 = new Book(11L, "title", "author", 1999, copies);
        //When
        BookDto resultBookDto = bookMapper.mapToBookDto(book1);
        //Then
        assertEquals(11L, resultBookDto.getBookId(), 0.01);
        assertEquals(1999, resultBookDto.getYearOfPublication());
        assertEquals(0, resultBookDto.getCopies().size());
    }

    @Test
    public void mapToBookDtoListTest() {
        //Given
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        books.add(new Book());
        books.add(new Book());
        //When
        List<BookDto> bookDtos = bookMapper.mapToBookDtoList(books);
        //Then
        assertNotNull(bookDtos);
        assertEquals(3, bookDtos.size());
    }
}
