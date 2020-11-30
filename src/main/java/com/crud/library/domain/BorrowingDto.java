package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BorrowingDto {

    private long copyId;
    private long userId;
    private LocalDate borrowingTheCopyDate;
    private LocalDate returnOfTheCopyDate;

    public BorrowingDto(long copyId, long userId) {
        this.copyId = copyId;
        this.userId = userId;
        this.borrowingTheCopyDate = LocalDate.now();
    }

    public void setReturnOfTheCopyDate(LocalDate returnOfTheCopyDate) {
        this.returnOfTheCopyDate = returnOfTheCopyDate;
    }
}
