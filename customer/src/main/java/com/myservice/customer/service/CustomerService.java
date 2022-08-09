package com.myservice.customer.service;

import com.myservice.customer.model.Customer;
import com.myservice.customer.repository.CustomerRepository;
import com.myservice.customer.model.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    public void register(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        customerRepository.save((customer));
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
