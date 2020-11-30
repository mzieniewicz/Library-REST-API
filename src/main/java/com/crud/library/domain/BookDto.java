package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDto {

    private long bookId;
    private String title;
    private String author;
    private int yearOfPublication;
    private List<CopyOfBook> copies = new ArrayList<>();

}
