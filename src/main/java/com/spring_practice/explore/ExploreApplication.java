package com.spring_practice.explore;

import com.spring_practice.explore.model.Customer;
import com.spring_practice.explore.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@SpringBootApplication
public class ExploreApplication {

    private static final Logger log = LoggerFactory.getLogger(ExploreApplication.class);
    private final AtomicInteger counter = new AtomicInteger(0);
    private static final String greeting = "Hello, %s!";

	public static void main(String[] args) {
		SpringApplication.run(ExploreApplication.class, args);
	}

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(greeting,  name));
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository){
        return args -> {

            customerRepository.save(new Customer("John", "Bauver"));
            customerRepository.save(new Customer("Jacob", "Smith"));
            customerRepository.save(new Customer("Miley", "Bauver"));
            customerRepository.save(new Customer("Kyle", "Bauver"));
            customerRepository.save(new Customer("John", "Kennedy"));

            log.info("Customers found with findAll():");
            for (Customer customer : customerRepository.findAll()) {
                log.info(customer.toString());
            }
            log.info(" ");

            log.info("Customers found with findByLastName('Bauver'):");
            customerRepository.findByLastName("Bauver").forEach(customer -> log.info(customer.toString()));
            log.info(" ");

            log.info("customer with id 1L");

            Customer customer = customerRepository.findById(1L);
            log.info(customer.toString());

        };

    }

}
