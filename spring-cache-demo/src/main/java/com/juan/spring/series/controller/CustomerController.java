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
        log.info("new Customer registration {}", request);
        Customer customer = customerService.registerCustomer(request);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> retrieveCustomer(@PathVariable long id) {
        log.info("Retrieve Customer by id {}", id);
        Customer optCustomer = customerService.retrieveCustomer(id);
        return new ResponseEntity<>(optCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> retrieveAllCustomer() {
        log.info("Retrieve all Customer");
        List<Customer> customers = customerService.retrieveAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody CustomerDTO
            customerDTO) {
        log.info("Updating Customer with id {}", id);
        Customer uptCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok().body(uptCustomer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable long id) {
        log.info("Deleting Customer with id {}", id);
        customerService.deleteCustomer(id);
    }
}
