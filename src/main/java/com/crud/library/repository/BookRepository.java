package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

//    @Query
//    List<Book> findBookByTitleFragment(@Param("FRAGMENT") String title);
//
//    @Query
//    List<Book> findBookByAuthorFragment(@Param("FRAGMENT") String author);

    @Override
    List<Book> findAll();

    @Override
    Book save(Book book);

    @Override
    Optional<Book> findById(Long bookId);

    @Override
    void deleteById(Long bookId);

    @Override
    long count();

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

}
