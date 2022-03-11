package com.github.zjiajun.spring.sandbox.company.example.web.model;

import lombok.Data;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/3/7 09:55
 */
@Data
public class ExampleDTO {

    private Long id;

    private Integer age;

    private String name;

    private boolean isVip;
}
