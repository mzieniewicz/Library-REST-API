package com.crud.library.repository;


import com.crud.library.domain.Book;
import com.crud.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long UserId);

    @Override
    void deleteById(Long UserId);

    @Override
    long count();

    List<User> findByUserSurname(String userSurname);
}
