package com.org.example.library_management_system.service;

import com.org.example.library_management_system.entity.Book;
import com.org.example.library_management_system.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book save(Book book) {
        if (book.getId() != null) {
            this.repository.findById(book.getId()).ifPresent((existing) -> {
                int borrowedOut = existing.getTotalCopies() - existing.getAvailableCopies();
                int newAvailableCopies = Math.max(0, book.getTotalCopies() - borrowedOut);
                book.setAvailableCopies(newAvailableCopies);
            });
        } else {
            book.setAvailableCopies(book.getTotalCopies());
        }

        return (Book)this.repository.save(book);
    }

    public List<Book> search(String keyword) {
        return keyword != null && !keyword.isBlank() ? this.repository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword) : this.repository.findAll();
    }

    public List<Book> findAll() {
        return this.repository.findAll();
    }

    public Book findById(Long id) {
        return repository.findById(id).orElse(null);    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
