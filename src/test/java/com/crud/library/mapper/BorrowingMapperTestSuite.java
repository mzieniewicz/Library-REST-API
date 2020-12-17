package com.crud.library.mapper;

import com.crud.library.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BorrowingMapperTestSuite {

    @Autowired
    private BorrowingMapper borrowingMapper;

    @Test
    public void mapToBorrowingTest() {
        //Given
        CopyOfBook copy = new CopyOfBook(11L, true);
        User user = new User();
        BorrowingDto borrowingDto = new BorrowingDto(1L, copy, user, LocalDate.now(), LocalDate.now().plusDays(30L) );
        //When
        Borrowing resultBorrowing = borrowingMapper.mapToBorrowing(borrowingDto);
        //Then
        assertEquals(1L, resultBorrowing.getBorrowingId(), 0.01);
        assertEquals(user.getClass(), resultBorrowing.getUser().getClass());
        assertEquals(copy.getClass(), resultBorrowing.getCopyOfBook().getClass());
        assertEquals(LocalDate.now(), resultBorrowing.getBorrowingTheCopyDate());
        assertEquals(LocalDate.now().plusDays(30L), resultBorrowing.getReturnOfTheCopyDate());
    }
    @Test
    public void mapToBorrowingDtoTest() {
        //Given
        CopyOfBook copy = new CopyOfBook(11L, true);
        User user = new User();
        Borrowing borrowing = new Borrowing(1L, copy, user, LocalDate.now(), LocalDate.now().plusDays(30L) );
        //When
        BorrowingDto resultBorrowingDto = borrowingMapper.mapToBorrowingDto(borrowing);
        //Then
        assertEquals(1L, resultBorrowingDto.getBorrowingId(), 0.01);
        assertEquals(user.getClass(), resultBorrowingDto.getUser().getClass());
        assertEquals(copy.getClass(), resultBorrowingDto.getCopyOfBook().getClass());
        assertEquals(LocalDate.now(), resultBorrowingDto.getBorrowingTheCopyDate());
        assertEquals(LocalDate.now().plusDays(30L), resultBorrowingDto.getReturnOfTheCopyDate());
    }
    @Test
    public void mapToBorrowingDtoListTest() {
        //Given
        List<Borrowing> borrowings = new ArrayList<>();
        borrowings.add(new Borrowing());
        borrowings.add(new Borrowing());
        borrowings.add(new Borrowing());
        //When
        List<BorrowingDto> borrowingDtos =borrowingMapper.mapToBorrowingDtoList(borrowings);
        //Then
        assertNotNull(borrowingDtos);
        assertEquals(3, borrowingDtos.size());
    }


}
