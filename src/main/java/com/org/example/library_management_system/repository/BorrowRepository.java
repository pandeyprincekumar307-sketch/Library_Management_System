package com.org.example.library_management_system.repository;

import com.org.example.library_management_system.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

}
