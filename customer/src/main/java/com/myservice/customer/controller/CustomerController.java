package com.myservice.customer.controller;

import com.myservice.customer.model.Customer;
import com.myservice.customer.model.CustomerRequest;
import com.myservice.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("")
    public void register(@RequestBody CustomerRequest request){
        log.info("New customer registration {}", request);
        customerService.register(request);
    }

    @GetMapping("")
    public List<Customer> getAll(){
        List<Customer> customers = customerService.getAll();
        log.info("{} customers returned", customers.size());
        return customers;
    }
}
