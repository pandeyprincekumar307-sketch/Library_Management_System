package com.org.example.library_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "books"
)
public class Book {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private @NotNull(
            message = "Title is required"
    ) String title;
    private @NotNull(
            message = "Author is required"
    ) String author;
    private @Min(
            value = 0L,
            message = "Total Copies cannot be negative"
    ) int totalCopies = 1;
    private @Min(
            value = 0L,
            message = "Available Copies cannot be negative"
    ) int availableCopies = 1;
    @OneToMany(
            mappedBy = "book",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY
    )
    private List<BorrowRecord> borrowRecords = new ArrayList<>();
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "book_category",
            joinColumns = {@JoinColumn(
                    name = "book_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "category_id"
            )}
    )
    private List<Category> categories = new ArrayList<>();

    public Book() {
    }

    public Book(String title, String author) {
        this.author = author;
        this.title = title;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalCopies() {
        return this.totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return this.availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return this.borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
