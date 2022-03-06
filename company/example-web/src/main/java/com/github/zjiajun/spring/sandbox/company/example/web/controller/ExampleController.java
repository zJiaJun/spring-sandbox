package com.github.zjiajun.spring.sandbox.company.example.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/3/6 15:09
 */
@RestController
@RequestMapping("/example")
public class ExampleController {

    @GetMapping("/health")
    public String health() {
        return "health";
    }
}
