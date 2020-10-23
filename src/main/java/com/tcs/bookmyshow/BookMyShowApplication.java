package com.tcs.bookmyshow;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableHystrix
@EnableHystrixDashboard
public class BookMyShowApplication {

    @Autowired
    private RestTemplate template;

    @HystrixCommand(commandKey = "java techie", groupKey = "java techie", fallbackMethod = "bookMyShowFallback")
    @GetMapping("/bookNow")
    public String bookMyShow() {
        String emailServiceRResponce = template.getForObject("http://localhost:8181/emailService/send", String.class);
        String payymentServiceRResponce = template.getForObject("http://localhost:8182/paymentService/pay", String.class);
        return emailServiceRResponce + "\n" + payymentServiceRResponce;
    }

    @GetMapping("/bookNowWihhoutHystrix")
    public String bookMyShowWihhoutHystrix() {
        String emailServiceRResponce = template.getForObject("http://localhost:8181/emailService/send", String.class);
        String payymentServiceRResponce = template.getForObject("http://localhost:8182/paymentService/pay", String.class);
        return emailServiceRResponce + "\n" + payymentServiceRResponce;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }

    public String bookMyShowFallback() {
        return "Service gateway is failed";
    }
}
