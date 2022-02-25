package com.juan.spring.series.controller;

import com.juan.spring.series.dto.CustomerDTO;
import com.juan.spring.series.model.Customer;
import com.juan.spring.series.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody CustomerDTO request) {
        Customer customer = customerService.registerCustomer(request);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Customer> retrieveCustomer(@PathVariable String dni) {
        Customer optCustomer = customerService.retrieveCustomerByDni(dni);
        return new ResponseEntity<>(optCustomer, HttpStatus.OK);
    }

    @GetMapping("/v2/{dni}")
    public ResponseEntity<Customer> retrieveCustomerV2(@PathVariable String dni) {
        Customer optCustomer = customerService.retrieveCustomerByDniV2(dni);
        return new ResponseEntity<>(optCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> retrieveAllCustomer() {
        List<Customer> customers = customerService.retrieveAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String dni, @RequestBody CustomerDTO
            customerDTO) {
        Customer customerToUpdate = Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .dni(customerDTO.getDni())
                .email(customerDTO.getEmail())
                .build();
        Customer uptCustomer = customerService.updateCustomer(customerToUpdate);
        return ResponseEntity.ok().body(uptCustomer);
    }

    @DeleteMapping("/{dni}")
    public void deleteCustomer(@PathVariable String dni) {
        customerService.deleteCustomer(dni);
    }
}
