package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.CopyOfBook;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowingRepository;
import com.crud.library.repository.CopyRepository;
import com.crud.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService service;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BorrowingRepository borrowingRepository;
    @Mock
    private CopyRepository copyRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    public void getAllBooksTest() {
        //Given
        List<CopyOfBook> copies = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        Book book1 = new Book(1L, "title1", "author1", 1999, copies);
        Book book2 = new Book(2L, "title2", "author2", 1997, copies);
        books.add(book1);
        books.add(book2);
        when(service.getAllBooks()).thenReturn(books);

        //When
        List<Book> resultList = service.getAllBooks();
        //Then
        assertEquals(2, resultList.size());
        assertEquals("title1", resultList.get(0).getTitle());
        assertEquals(2L, resultList.get(1).getBookId(), 0.01);
        // CleanUp
        bookRepository.deleteById(book1.getBookId());
        bookRepository.deleteById(book2.getBookId());
    }

    @Test
    public void getNullBookTest() {
        //Given
        when(bookRepository.findById(5L)).thenReturn(null);
        //When
        Optional<Book> resultBook = service.getBook(5L);
        //Then
        assertNull(resultBook);
    }

    @Test
    public void saveBookTest() {
        //Given
        Book book = new Book(1L, "title1", "author1", 1999, new ArrayList<>());
        when(bookRepository.save(book)).thenReturn(book);
        //When
        Book resultBook = service.saveBook(book);
        //Then
        assertEquals(1L, resultBook.getBookId(), 0.01);
        assertEquals("title1", resultBook.getTitle());
        assertEquals("author1", resultBook.getAuthor());
    }

}
