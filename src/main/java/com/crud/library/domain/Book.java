package com.crud.library.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@NamedQueries({
//        @NamedQuery(
//                name = "Book.findBookByTitleFragment",
//                query = "FROM BOOK WHERE title LIKE CONCAT('%', :FRAGMENT, '%')"),
//        @NamedQuery(
//                name = "Book.findBookByAuthorFragment",
//                query = "FROM BOOK WHERE author LIKE CONCAT('%', :FRAGMENT, '%')")
//})

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "BOOKS")
public final class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private long bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "YEAR_OF_PUBLICATION")
    private int yearOfPublication;

    @OneToMany(
            targetEntity = CopyOfBook.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CopyOfBook> copies = new ArrayList<>();
}
