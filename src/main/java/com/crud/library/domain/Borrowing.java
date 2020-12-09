package com.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "BORROWING")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private long borrowingId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "COPY_ID")
    private CopyOfBook copyOfBook;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "BORROWING_DATE")
    private LocalDate borrowingTheCopyDate;

    @Column(name = "RETURN_DATE")
    private LocalDate returnOfTheCopyDate;


    public Borrowing(long borrowingId, CopyOfBook copyOfBook, User user) {
        this.borrowingId = borrowingId;
        this.copyOfBook = copyOfBook;
        this.user = user;
        this.borrowingTheCopyDate = LocalDate.now();
        this.returnOfTheCopyDate = LocalDate.now().plusDays(30L);
    }

    public void extendReturnOfTheCopyDate(LocalDate returnOfTheCopyDate) {
        if(!returnOfTheCopyDate.isAfter(LocalDate.now()))
        this.returnOfTheCopyDate=this.returnOfTheCopyDate.plusDays(30);
    }
}
