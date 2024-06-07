package com.customer.challenge.controllers;

import com.customer.challenge.entities.core.Customer;
import com.customer.challenge.entities.dto.InputCustomer;
import com.customer.challenge.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody InputCustomer inputCustomer){
        return ResponseEntity.ok(customerService.saveCustomer(inputCustomer));
    }

    @PutMapping
    public ResponseEntity<Customer> editCustomerCustomer(@RequestBody InputCustomer inputCustomer){
        return ResponseEntity.ok(customerService.editCustomer(inputCustomer));
    }

    @GetMapping(("/{idCustomer}"))
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long idCustomer){
        return ResponseEntity.ok(customerService.findCustomerById(idCustomer));
    }

    @PutMapping("/delete")
    public ResponseEntity deleteCustomerById(@RequestParam Long idCustomer){
        customerService.deleteCustomerById(idCustomer);
        return ResponseEntity.ok().build();
    }
}
