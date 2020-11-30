package com.crud.library.repository;


import com.crud.library.domain.Book;
import com.crud.library.domain.CopyOfBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CopyRepository extends CrudRepository<CopyOfBook, Long> {

    @Override
    List<CopyOfBook> findAll();

    @Override
    CopyOfBook save(CopyOfBook copyOfBook);

    @Override
    Optional<CopyOfBook> findById(Long copyId);

    @Override
    void deleteById(Long copyId);

    @Override
    long count();
}
