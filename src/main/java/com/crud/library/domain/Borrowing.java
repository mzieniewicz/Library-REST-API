package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Borrowing {

    private long copyId;
    private long userId;
    private LocalDate borrowingTheCopyDate;
    private LocalDate returnOfTheCopyDate;

    public Borrowing(long copyId, long userId) {
        this.copyId = copyId;
        this.userId = userId;
        this.borrowingTheCopyDate = LocalDate.now();
    }

    public void setReturnOfTheCopyDate(LocalDate returnOfTheCopyDate) {
        this.returnOfTheCopyDate = returnOfTheCopyDate;
    }
}
