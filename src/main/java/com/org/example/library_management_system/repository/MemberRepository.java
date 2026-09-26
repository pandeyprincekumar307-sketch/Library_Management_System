package com.org.example.library_management_system.repository;

import com.org.example.library_management_system.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
