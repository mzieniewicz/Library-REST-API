package com.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "READER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "USER_ID", unique = true)
    private long userId;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "USERSURNAME")
    private String userSurname;

    @Column(name = "PESEL")
    private int pesel;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "ACCOUNT_CREATION_DATE")
    private LocalDate accountCreationDate;

    @Column(name = "ACCOUNT_CREATION_DATE")
    private BigDecimal accountBalance;

    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = Borrowing.class,
            mappedBy = "user"
    )
    private List<Borrowing> borrowings = new ArrayList<>();

    public User(long userId, String userName, String userSurname, int PESEL, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.pesel = pesel;
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
