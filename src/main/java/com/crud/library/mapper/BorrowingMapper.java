package com.crud.library.mapper;

import com.crud.library.domain.Borrowing;
import com.crud.library.domain.BorrowingDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowingMapper {

    public Borrowing mapToBorrowing(final BorrowingDto borrowingDto) {
        return new Borrowing(
                borrowingDto.getCopyId(),
                borrowingDto.getUserId(),
                borrowingDto.getBorrowingTheCopyDate(),
                borrowingDto.getReturnOfTheCopyDate());
    }

    public BorrowingDto mapToBorrowingDto(final Borrowing borrowing) {
        return new BorrowingDto(
                borrowing.getCopyId(),
                borrowing.getUserId(),
                borrowing.getBorrowingTheCopyDate(),
                borrowing.getReturnOfTheCopyDate());
    }

    public List<BorrowingDto> mapToBorrowingDtoList(final List<Borrowing> borrowings) {
        return borrowings.stream()
                .map(b -> new BorrowingDto(b.getCopyId(), b.getUserId(), b.getBorrowingTheCopyDate(),b.getReturnOfTheCopyDate()))
                .collect(Collectors.toList());
    }
}
