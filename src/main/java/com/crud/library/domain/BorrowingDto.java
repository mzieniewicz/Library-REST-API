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
    private long copyId;
    private long userId;
    private LocalDate borrowingTheCopyDate;
    private LocalDate returnOfTheCopyDate;

    public BorrowingDto(long borrowingId, LocalDate borrowingTheCopyDate, LocalDate returnOfTheCopyDate) {
        this.borrowingId = borrowingId;
        this.borrowingTheCopyDate = borrowingTheCopyDate;
        this.returnOfTheCopyDate = returnOfTheCopyDate;
    }

    public void setReturnOfTheCopyDate(LocalDate returnOfTheCopyDate) {
        this.returnOfTheCopyDate = returnOfTheCopyDate;
    }
}
