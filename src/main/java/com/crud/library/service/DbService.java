package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Borrowing;
import com.crud.library.domain.CopyOfBook;
import com.crud.library.domain.User;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowingRepository;
import com.crud.library.repository.CopyRepository;
import com.crud.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getBookTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBookAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(final Long bookid) {
        bookRepository.deleteById(bookid);
    }

    public List<CopyOfBook> getAllCopies() {
        return copyRepository.findAll();
    }

    public Optional<CopyOfBook> getCopyById(final Long copyId)  {
        return copyRepository.findById(copyId);
    }

    public void deleteCopy(final Long copyId) {
        copyRepository.deleteById(copyId);
    }

    public CopyOfBook saveCopy(final CopyOfBook copyOfBook) {
        return copyRepository.save(copyOfBook);
    }

    public List<Borrowing> getBorrowings() {
        return borrowingRepository.findAll();
    }

    public Optional<Borrowing> getBorrowing(Long borrowingId) {
        return borrowingRepository.findById(borrowingId);
    }
    
    @Transactional
    public Borrowing saveBorrowing(final Borrowing borrowing) {
        CopyOfBook copy = borrowing.getCopyOfBook();
        if(copy.isEligible()){
           copy.setEligible(false);
        }else{
            throw new CopyIsNotAvalible();
        }
        return borrowingRepository.save(borrowing);
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> getUsersBySurname(String userSurname) {
        return userRepository.findByUserSurname(userSurname);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

}
