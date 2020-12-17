package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.CopyOfBook;
import com.crud.library.domain.CopyOfBookDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CopyMapperTestSuite {

    @Autowired
    private CopyMapper copyMapper;

    @Test
    public void mapToCopyOfBookTest(){
        //Given
        CopyOfBookDto copyOfBookDto = new CopyOfBookDto(1L, true);
        //When
        CopyOfBook resultCopy = copyMapper.mapToCopyOfBook(copyOfBookDto);
        //Then
        assertEquals(1L, resultCopy.getCopyId(), 0.01);
        assertTrue(resultCopy.isEligible());
    }
    @Test
    public void mapToCopyOfBookDtoTest(){
        //Given
        List<CopyOfBook> copies = new ArrayList<>();
        Book book1 = new Book(11L, "title", "author", 1999, copies);
        CopyOfBook copyOfBook = new CopyOfBook(1L, true, book1);
        //When
        CopyOfBookDto resultCopyDto = copyMapper.mapToCopyOfBookDto(copyOfBook);
        //Then
        assertEquals(1L, resultCopyDto.getCopyId(), 0.01);
        assertTrue(resultCopyDto.isEligible());
        assertEquals(11L, resultCopyDto.getBook().getBookId());
    }
    @Test
    public void mapToCopyOfBookDtoListTest(){
        List<CopyOfBook> copyOfBooks = new ArrayList<>();
        copyOfBooks.add(new CopyOfBook());
        copyOfBooks.add(new CopyOfBook());
        //When
        List<CopyOfBookDto> resultDtos = copyMapper.mapToCopyOfBookDtoList(copyOfBooks);
        //Then
        assertNotNull(resultDtos);
        assertEquals(2, resultDtos.size());

    }

}
