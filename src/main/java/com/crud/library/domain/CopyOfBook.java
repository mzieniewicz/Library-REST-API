package com.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "COPIES")
public class CopyOfBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "COPY_ID", unique = true)
    private long copyId;

    @Column(name = "COPY_IS_ELIGIBLE")
    private boolean isEligible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_BOOK_ID")
    private Book book;

    public CopyOfBook(long copyId, boolean isEligible) {
        this.copyId = copyId;
        this.isEligible = isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }
}
