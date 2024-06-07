package com.customer.challenge.controllers;

import com.customer.challenge.entities.core.Customer;
import com.customer.challenge.entities.dto.InputCustomer;
import com.customer.challenge.services.CustomerService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

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
    public void shouldCallServiceAndReturnStatus200WhenGetAllCustomers(){
        List<Customer> customerList = new ArrayList<>();
        when(customerService.findAllCustomers()).thenReturn(customerList);

        ResponseEntity<List<Customer>> result = customerController.findAllCustomers();

        verify(customerService, times(1)).findAllCustomers();
        assertEquals(result.getStatusCode().value(), 200);
        assertEquals(result.getBody(), customerList);
    }

    @Test
    public void shouldCallServiceAndReturnStatus200WhenSaveCustomer(){

        when(customerService.saveCustomer(inputCustomer)).thenReturn(customer);

        ResponseEntity<Customer> result = customerController.saveCustomer(inputCustomer);

        verify(customerService, times(1)).saveCustomer(inputCustomer);
        assertEquals(result.getStatusCode().value(), 200);
        assertEquals(result.getBody(), customer);
    }

    @Test
    public void shouldCallServiceAndReturnStatus200WhenEditCustomer(){

        when(customerService.editCustomer(inputCustomer)).thenReturn(customer);

        ResponseEntity<Customer> result = customerController.editCustomerCustomer(inputCustomer);

        verify(customerService, times(1)).editCustomer(inputCustomer);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void shouldCallServiceAndReturnStatus200WhenDeleteCustomerById(){

        Long idCustomer = 1L;

        doNothing().when(customerService).deleteCustomerById(anyLong());

        ResponseEntity result = customerController.deleteCustomerById(idCustomer);

        verify(customerService, times(1)).deleteCustomerById(idCustomer);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void shouldCallServiceAndReturnStatus200WhenFindCustomerById(){

        Long idCustomer = 1L;

        when(customerService.findCustomerById(anyLong())).thenReturn(customer);

        ResponseEntity<Customer> result = customerController.findCustomerById(idCustomer);

        verify(customerService, times(1)).findCustomerById(idCustomer);
        assertEquals(result.getStatusCode().value(), 200);
    }


}
