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
    @Column(name = "BORROWING_ID", unique = true)
    private long borrowingId;

    @NotNull
    @Column(name = "BORROWING_DATE")
    private LocalDate borrowingTheCopyDate;

    @Column(name = "RETURN_DATE")
    private LocalDate returnOfTheCopyDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "COPY_ID")
    private CopyOfBook copyOfBook;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Borrowing(long borrowingId, LocalDate borrowingTheCopyDate, LocalDate returnOfTheCopyDate) {
        this.borrowingId = borrowingId;
        this.borrowingTheCopyDate = LocalDate.now();
        this.returnOfTheCopyDate = LocalDate.now().plusDays(30L);
    }

    public void setReturnOfTheCopyDate(LocalDate returnOfTheCopyDate) {
        this.returnOfTheCopyDate = returnOfTheCopyDate;
    }
}
