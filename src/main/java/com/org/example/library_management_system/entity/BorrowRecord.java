package com.org.example.library_management_system.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "borrow_records"
)
public class BorrowRecord {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private LocalDate issueDate;
    private LocalDate returnDate;
    @Enumerated(EnumType.STRING)
    private BorrowStatus status;
    @ManyToOne
    @JoinColumn(
            name = "member_id"
    )
    private Member member;
    @ManyToOne
    @JoinColumn(
            name = "book_id"
    )
    private Book book;

    public BorrowRecord() {
        this.status = BorrowRecord.BorrowStatus.BORROWED;
    }

    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BorrowStatus getStatus() {
        return this.status;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public static enum BorrowStatus {
        BORROWED,
        RETURNED;

        private BorrowStatus() {
        }
    }
}

