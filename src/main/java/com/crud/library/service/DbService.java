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

    public Optional<Book> getBookTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Optional<Book> getBookAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

//    public Book saveBook(final Book book) {
//        return bookRepository.save(book);
//    }

//    public void deleteBook(final Long bookid) {
//        bookRepository.deleteById(bookid);
//    }

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

    public void updateIsEligible(Long copyId, boolean isEligible) throws Exception {
        Optional<CopyOfBook> copy = copyRepository.findById(copyId);
        copy.get().isEligible();
        if(isEligible){
            isEligible = false;
        }else{
            isEligible = true;
        }
        copyRepository.save(copy.orElseThrow(() -> new Exception()));
    }

    public List<Borrowing> getBorrowings() {
        return borrowingRepository.findAll();
    }

    public Optional<Borrowing> getBorrowing(Long borrowingId) {
        return borrowingRepository.findById(borrowingId);
    }
    

    public Borrowing saveBorrowing(final Borrowing borrowing) {
        return borrowingRepository.save(borrowing);
    }

    public void deleteBorrowing(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }

}
