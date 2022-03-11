package com.github.zjiajun.spring.sandbox.company.example.web.controller;

import com.github.zjiajun.spring.sandbox.company.example.web.model.ExampleDTO;
import com.github.zjiajun.spring.sandbox.company.example.web.model.query.ExampleQueryDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/3/6 15:09
 */
@RestController
@RequestMapping("/example")
public class ExampleController {

    @GetMapping("/get")
    public String health(@RequestParam("param") String param) {
        return "example get " + param;
    }

    @PostMapping("/post")
    public ExampleDTO post(@RequestBody ExampleQueryDTO exampleQueryDTO) {
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setId(exampleQueryDTO.getId());
        exampleDTO.setAge(30);
        exampleDTO.setName(exampleQueryDTO.getName());
        exampleDTO.setVip(true);
        return exampleDTO;
    }
}
