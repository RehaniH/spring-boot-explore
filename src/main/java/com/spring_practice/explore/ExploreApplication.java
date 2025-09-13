package com.spring_practice.explore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@SpringBootApplication
public class ExploreApplication {

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

}
