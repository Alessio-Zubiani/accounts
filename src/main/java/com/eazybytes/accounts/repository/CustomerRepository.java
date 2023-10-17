package com.eazybytes.accounts.repository;

import java.util.Optional;

import com.eazybytes.accounts.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByMobileNumber(String mobileNumber);

}
