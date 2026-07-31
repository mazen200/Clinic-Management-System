package com.netpoint.clinic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// controller+responsebody
@RequestMapping("/api/test")
public class HelloWorldController {

    @GetMapping()
    String sayhello()
    {
        return "Hello World!";
    }



}
