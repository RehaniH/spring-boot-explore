package com.spring_practice.explore.repository;

import com.spring_practice.explore.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastNames);
    Customer findById(long id);
}
