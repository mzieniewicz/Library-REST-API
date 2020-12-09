package com.crud.library.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CopyOfBookDto {

    private long copyId;
    private boolean isEligible;
    private BookDto bookDto;

    public CopyOfBookDto(long copyId, boolean isEligible) {
        this.copyId = copyId;
        this.isEligible = isEligible;
    }
}
