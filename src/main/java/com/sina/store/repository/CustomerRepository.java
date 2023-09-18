package com.sina.store.repository;

import com.sina.store.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> getByMailAddress(String mailAddress);

    Optional<Customer> getByMobileNo(String mobileNo);

    Optional<Customer> getByCreditCardNumber(String creditCardNumber);

}
