package com.juan.spring.series.service;

import com.juan.spring.series.dto.CustomerDTO;
import com.juan.spring.series.exception.ResourceNotFoundException;
import com.juan.spring.series.model.Customer;
import com.juan.spring.series.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(CustomerDTO request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        customerRepository.save(customer);
        return customer;
    }

    public Customer retrieveCustomer(Long id) {
        log.info("retrieve customer by id {} from DB", id);
        return customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found customer by id "+ id));
    }

    public List<Customer> retrieveAllCustomer() {
        log.info("Retrieve all user from DB");
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> optCustomer = customerRepository.findById(id);
        if (!optCustomer.isPresent()) {
            throw new ResourceNotFoundException("Not found customer by id "+ id);
        }
        Customer customerToUpdate = optCustomer.get();
        customerToUpdate.setFirstName(customerDTO.getFirstName());
        customerToUpdate.setLastName(customerDTO.getLastName());
        customerToUpdate.setEmail(customerDTO.getEmail());
        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(Long id) {
        Customer customerToDelete = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found customer by id "+ id));
        customerRepository.delete(customerToDelete);
    }


}
