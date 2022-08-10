package com.myservice.fraud;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {

    private final FraudCheckHistoryService fchService;

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId){
        boolean isFraudulent = fchService.isFraudulentCustomer(customerId);

        log.info("Fraud check request for customer: {}", customerId);

        return new FraudCheckResponse(isFraudulent);
    }
}
