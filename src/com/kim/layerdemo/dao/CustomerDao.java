package com.kim.layerdemo.dao;

import com.kim.layerdemo.domain.Customer;

import java.util.List;

public interface CustomerDao {
    Customer create(Customer customer);

    void modify(Customer customer);

    void remove(Customer customer);

    Customer findByPK(String pk);

    List<Customer> findAll();
}
