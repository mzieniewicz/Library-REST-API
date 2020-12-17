package com.crud.library.mapper;

import com.crud.library.domain.CopyOfBook;
import com.crud.library.domain.CopyOfBookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {

    public CopyOfBook mapToCopyOfBook(final CopyOfBookDto copyOfBookDto) {
        return new CopyOfBook(
                copyOfBookDto.getCopyId(),
                copyOfBookDto.isEligible(),
                copyOfBookDto.getBook());
    }

    public CopyOfBookDto mapToCopyOfBookDto(final CopyOfBook copyOfBook) {
        return new CopyOfBookDto(
                copyOfBook.getCopyId(),
                copyOfBook.isEligible(),
                copyOfBook.getBook());
    }

    public List<CopyOfBookDto> mapToCopyOfBookDtoList(final List<CopyOfBook> copies) {
        return copies.stream()
                .map(c -> new CopyOfBookDto(c.getCopyId(),c.isEligible(),c.getBook()))
                .collect(Collectors.toList());
    }
}
