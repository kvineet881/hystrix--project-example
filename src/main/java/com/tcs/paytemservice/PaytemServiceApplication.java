package com.tcs.paytemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/paymentService")
public class PaytemServiceApplication {

    @GetMapping("/pay")
    public String payNow()
    {
        return "Payment service called .....";
    }

    public static void main(String[] args) {
        SpringApplication.run(PaytemServiceApplication.class, args);
    }

}
