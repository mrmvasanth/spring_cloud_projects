package com.packs.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class HelloController {
    private AtomicLong counter = new AtomicLong();

    @GetMapping("/hello")
    public HelloObject getHelloWordObject() {
        HelloObject hello = new HelloObject();
        hello.setMessage("Visitor No: " + counter.incrementAndGet());
        return hello;
    }
}