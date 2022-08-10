package com.myservice.customer.service;

import com.myservice.customer.model.Customer;
import com.myservice.customer.model.FraudCheckResponse;
import com.myservice.customer.repository.CustomerRepository;
import com.myservice.customer.model.CustomerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void register(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        //save customer
        customerRepository.saveAndFlush(customer);

        //check if fraudster
        FraudCheckResponse response = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(response.getIsFraudster()){
            throw new IllegalStateException("fraudster");
        }

        //send notification
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
