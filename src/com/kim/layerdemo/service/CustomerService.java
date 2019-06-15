package com.kim.layerdemo.service;

import com.kim.layerdemo.domain.Customer;

import java.util.List;

public interface CustomerService {
    boolean login(Customer customer);

    boolean register(Customer customer);

    List<Customer> search();

}
