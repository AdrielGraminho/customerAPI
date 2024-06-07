package com.customer.challenge.services.impl;

import com.customer.challenge.entities.core.Customer;
import com.customer.challenge.entities.dto.InputCustomer;
import com.customer.challenge.exceptions.InvalidCustomerIdException;
import com.customer.challenge.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    private Customer customer;

    private InputCustomer inputCustomer;

    @BeforeEach
    public void setUp(){
        customer = Customer.builder()
                .idCustomer(1L)
                .name("adriel")
                .email("adriel@gmail.com")
                .cpf("647389209387")
                .dateOfBirth(new Date())
                .build();

        inputCustomer = InputCustomer
                .builder()
                .idCustomer(1L)
                .name("adriel")
                .email("adriel@gmail.com")
                .cpf("647389209387")
                .dateOfBirth(new Date())
                .build();
    }

    @Test
    public void shouldCallRepositoryAndReturnCustomerListWhenFindAllCustomers(){
        List<Customer> customerList = List.of(customer);
        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> result = customerService.findAllCustomers();

        verify(customerRepository, times(1)).findAll();
        assertEquals(result, customerList);

    }

    @Test
    public void shouldCallRepositoryAndReturnCustomerWhenSaveCustomer(){
        inputCustomer.setIdCustomer(null);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.saveCustomer(inputCustomer);

        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals(result, customer);

    }

    @Test
    public void shouldCallRepositoryAndReturnCustomerWhenEditCustomer(){

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        Customer result = customerService.editCustomer(inputCustomer);

        verify(customerRepository, times(1)).save(any(Customer.class));
        assertEquals(result, customer);

    }


    @Test
    public void shouldCallRepositoryAndReturnCustomerWhenFindCustomerById(){

        Long customerId = 1L;
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        Customer result = customerService.findCustomerById(customerId);

        verify(customerRepository, times(1)).findById(customerId);
        assertEquals(result, customer);

    }

    @Test
    public void shouldCallRepositoryAndReturnExceptionWhenFindCustomerByIdAndNotFound(){

        Long customerId = 1L;
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        String message = "";
        try {
            customerService.findCustomerById(customerId);
        } catch (InvalidCustomerIdException invalidCustomerIdException){
            message = invalidCustomerIdException.getMessage();
        }

        verify(customerRepository, times(1)).findById(customerId);
        assertEquals("Customer not found", message);

    }

    @Test
    public void shouldCallRepositoryWhenDeleteCustomer(){

        Long customerId = 1L;

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).delete(any(Customer.class));

        customerService.deleteCustomerById(customerId);


        verify(customerRepository, times(1)).delete(customer);


    }

    @Test
    public void shouldCallRepositoryAndReturnExceptionWhenSaveCustomerAndCpfAndCnpjAreNull(){

        inputCustomer.setCnpj(null);
        inputCustomer.setCpf(null);
        inputCustomer.setIdCustomer(null);

        String message = "";
        try {
            customerService.saveCustomer(inputCustomer);
        } catch (InvalidCustomerIdException invalidCustomerIdException){
            message = invalidCustomerIdException.getMessage();
        }

        assertEquals("Customer CPF or CNPJ must be provided", message);

    }

}
