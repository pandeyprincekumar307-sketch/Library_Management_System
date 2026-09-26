package com.org.example.library_management_system.repository;

import com.org.example.library_management_system.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
