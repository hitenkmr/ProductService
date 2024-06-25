package com.scaler.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// this class will be hosting a set of API's
@RestController
@RequestMapping("/say")
public class SampleController {

    @GetMapping("/hello/{name}/{age}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("age") int age) {
        return "Hello " + name + " your age is some age amm like: " + age;
    }

    @GetMapping("/bye")
    public String sayBye() {
        return "Bye Everyone!";
    }
}
