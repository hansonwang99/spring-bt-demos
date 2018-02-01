package com.example;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @ApiOperation("test")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "success!";
    }

    @ApiOperation("test2")
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(@RequestParam String param) {
        return "Running : " + param;
    }
}
