package com.org.example.library_management_system.service;

import com.org.example.library_management_system.entity.Book;
import com.org.example.library_management_system.entity.BorrowRecord;
import com.org.example.library_management_system.entity.Member;
import com.org.example.library_management_system.repository.BookRepository;
import com.org.example.library_management_system.repository.BorrowRepository;
import com.org.example.library_management_system.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
        this.memberRepository = memberRepository;
    }

    public List<BorrowRecord> getAllRecord() {
        return this.borrowRepository.findAll();
    }

    public BorrowRecord findById(Long id) {
        return (BorrowRecord)this.borrowRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Borrow record not found with id: " + id));
    }

    @Transactional
    public BorrowRecord issueBook(Long bookId, Long memberId) {
        Book book = (Book)this.bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));
        Member member = (Member)this.memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No available copies of " + book.getTitle() + " to issue");
        } else {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookRepository.save(book);
            BorrowRecord borrowRecord = new BorrowRecord();
            borrowRecord.setBook(book);
            borrowRecord.setMember(member);
            borrowRecord.setIssueDate(LocalDate.now());
            borrowRecord.setStatus(BorrowRecord.BorrowStatus.BORROWED);
            return (BorrowRecord)this.borrowRepository.save(borrowRecord);
        }
    }

    @Transactional
    public BorrowRecord returnBook(Long recordId) {
        BorrowRecord record = (BorrowRecord)this.borrowRepository.findById(recordId).orElseThrow(() -> new IllegalArgumentException("Borrow record not found with id: " + recordId));
        if (record.getStatus() == BorrowRecord.BorrowStatus.RETURNED) {
            return record;
        } else {
            record.setStatus(BorrowRecord.BorrowStatus.RETURNED);
            record.setReturnDate(LocalDate.now());
            Book book = record.getBook();
            book.setAvailableCopies(Math.min(book.getTotalCopies(), book.getAvailableCopies() + 1));
            this.bookRepository.save(book);
            return (BorrowRecord)this.borrowRepository.save(record);
        }
    }

    public void delete(Long id) {
        this.borrowRepository.deleteById(id);
    }
}
