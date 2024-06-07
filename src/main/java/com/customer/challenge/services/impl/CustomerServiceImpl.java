package com.customer.challenge.services.impl;

import com.customer.challenge.entities.core.Customer;
import com.customer.challenge.entities.dto.InputCustomer;
import com.customer.challenge.exceptions.InvalidCustomerIdException;
import com.customer.challenge.repositories.CustomerRepository;
import com.customer.challenge.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(InputCustomer inputCustomer) {
        validCustomerInputToSave(inputCustomer);
        return customerRepository.save(buildCustomer(inputCustomer));
    }

    @Override
    public Customer editCustomer(InputCustomer inputCustomer) {
        validCustomerInputToEdit(inputCustomer);
        Customer oldCustomer = findCustomerById(inputCustomer.getIdCustomer());
        Customer newCustomer = buildCustomer(inputCustomer);
        newCustomer.setIdCustomer(oldCustomer.getIdCustomer());
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer findCustomerById(Long idCustomer) {
        return customerRepository.findById(idCustomer).orElseThrow(() -> new InvalidCustomerIdException("Customer not found"));
    }

    @Override
    public void deleteCustomerById(Long idCustomer) {
        Customer customer = findCustomerById(idCustomer);
        customerRepository.delete(customer);
    }

    private void validCustomerInputToEdit(InputCustomer inputCustomer) {
        if (Objects.isNull(inputCustomer.getIdCustomer())){
            throw new InvalidCustomerIdException("Customer ID must be not null when edit customer");
        }
    }

    private void validCustomerInputToSave(InputCustomer inputCustomer) {
        if (Objects.nonNull(inputCustomer.getIdCustomer())){
            throw new InvalidCustomerIdException("Customer ID must be null when creating a new customer");
        }
    }

    private Customer buildCustomer(InputCustomer inputCustomer) {
        return Customer.builder()
                .idCustomer(inputCustomer.getIdCustomer())
                .name(inputCustomer.getName())
                .email(inputCustomer.getEmail())
                .cpf(inputCustomer.getCpf())
                .dateOfBirth(inputCustomer.getDateOfBirth())
                .build();
    }
}
