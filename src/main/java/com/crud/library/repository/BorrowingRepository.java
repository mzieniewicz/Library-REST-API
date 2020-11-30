package com.crud.library.repository;

import com.crud.library.domain.Borrowing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {
    @Override
    List<Borrowing> findAll();

    @Override
    Borrowing save(Borrowing borrowing);

    @Override
    Optional<Borrowing> findById(Long borrowingId);

    @Override
    void deleteById(Long borrowingId);

    @Override
    long count();
}
