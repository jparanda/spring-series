package com.juan.spring.series.service;

import com.juan.spring.series.dto.CustomerDTO;
import com.juan.spring.series.exception.ResourceNotFoundException;
import com.juan.spring.series.model.Customer;
import com.juan.spring.series.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(cacheNames = "customers" , key = "#dni", condition = "#dni.length == 8")
    public Customer retrieveCustomerByDni(String dni) {
        log.info("retrieve customer by dni {} from DB", dni);
        return customerRepository.findByDni(dni)
                .orElseThrow(()->new ResourceNotFoundException("Not found customer by dni "+ dni));
    }

    @Cacheable(cacheNames = "customers" , key = "#dni", unless = "#result.getAge() < 18")
    public Customer retrieveCustomerByDniV2(String dni) {
        log.info("retrieve customer by dni {} from DB", dni);
        return customerRepository.findByDni(dni)
                .orElseThrow(()->new ResourceNotFoundException("Not found customer by dni "+ dni));
    }

    public Customer registerCustomer(CustomerDTO request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dni(request.getDni())
                .age(request.getAge())
                .email(request.getEmail())
                .build();

        return customerRepository.save(customer);
    }

    @CachePut(value = "customers", key = "#customer.dni")
    public Customer updateCustomer(Customer customer) {
        log.info("Updating Customer with dni {}", customer.getDni());
        Optional<Customer> optCustomer = customerRepository.findByDni(customer.getDni());
        if (!optCustomer.isPresent()) {
            throw new ResourceNotFoundException("Not found customer by dni " + customer.getDni());
        }
        return customerRepository.save(customer);
    }

    @CacheEvict(value = "customers", key = "#dni")
    public void deleteCustomer(String dni) {
        log.info("Deleting Customer with dni {}", dni);
        Customer customerToDelete = customerRepository.findByDni(dni)
                .orElseThrow(()->new ResourceNotFoundException("Not found customer by dni " + dni));
        customerRepository.delete(customerToDelete);
    }

    public List<Customer> retrieveAllCustomer() {
        log.info("Retrieve all user from DB");
        return customerRepository.findAll();
    }


}
