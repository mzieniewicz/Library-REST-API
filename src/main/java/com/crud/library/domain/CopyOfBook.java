package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CopyOfBook {

    private long copyId;
    private long bookId;   // czy utworzyć pole private Book book;
    private boolean isEligible;

}
