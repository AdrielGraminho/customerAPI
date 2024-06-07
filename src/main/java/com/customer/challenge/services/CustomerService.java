package com.customer.challenge.services;

import com.customer.challenge.entities.core.Customer;
import com.customer.challenge.entities.dto.InputCustomer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomers();

    Customer saveCustomer(InputCustomer inputCustomer);

    Customer editCustomer(InputCustomer inputCustomer);

    Customer findCustomerById(Long idCustomer);

    void deleteCustomerById(Long idCustomer);
}
