package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BorrowingDto {

    private long borrowingId;
    private CopyOfBook copyOfBook;
    private User user;
    private LocalDate borrowingTheCopyDate;
    private LocalDate returnOfTheCopyDate;

    public BorrowingDto(long borrowingId, CopyOfBook copyOfBook, User user) {
        this.borrowingId = borrowingId;
        this.copyOfBook = copyOfBook;
        this.user = user;
        this.borrowingTheCopyDate = LocalDate.now();
        this.returnOfTheCopyDate = LocalDate.now().plusDays(30L);
    }



}
