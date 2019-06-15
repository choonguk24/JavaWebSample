package com.kim.layerdemo.service.imp;

import com.kim.layerdemo.dao.CustomerDao;
import com.kim.layerdemo.dao.imp.CustomerDaoImpJdbc;
import com.kim.layerdemo.domain.Customer;
import com.kim.layerdemo.service.CustomerService;

import java.util.List;

public class CustomerServiceImp implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpJdbc();

    @Override
    public boolean login(Customer customer) {
        Customer dbCustomer = customerDao.findByPK(customer.getId());
        if (dbCustomer.getPassword().equals(customer.getPassword())) {
//            登入成功
            customer.setPhone(dbCustomer.getPhone());
            customer.setAddress(dbCustomer.getAddress());
            customer.setBirthday(dbCustomer.getBirthday());
            customer.setName(dbCustomer.getName());
            return true;
        }
        return false;
    }

    @Override
    public boolean register(Customer customer) {
        return true;
    }

    @Override
    public List<Customer> search() {
        return null;
    }
}
