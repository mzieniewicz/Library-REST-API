package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class User {

    private long userId;
    private String userName;
    private String userSurname;
    private int PESEL;
    private String userEmail;
    private LocalDate accountCreationDate;
    private BigDecimal accountBalance;
    private List<Borrowing> borrowings = new ArrayList<>();

    public User(long userId, String userName, String userSurname, int PESEL, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.PESEL = PESEL;
        this.userEmail = userEmail;
        this.accountCreationDate = LocalDate.now();
        this.accountBalance = BigDecimal.ZERO;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
}
