package com.github.zjiajun.spring.sandbox.polling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2021/10/8 21:22
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {
        PollsApplication.class,
        Jsr310Converters.class
})
public class PollsApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(PollsApplication.class, args);
    }

}
