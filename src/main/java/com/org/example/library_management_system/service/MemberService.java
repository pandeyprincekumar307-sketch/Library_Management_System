package com.org.example.library_management_system.service;

import com.org.example.library_management_system.entity.Member;
import com.org.example.library_management_system.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Member save(Member member) {
        return (Member)this.repository.save(member);
    }

    public List<Member> findAll() {
        return this.repository.findAll();
    }

    public Member findById(Long id) {
        return (Member)this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found with id " + id));
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
