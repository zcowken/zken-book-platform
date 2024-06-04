package com.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@SpringBootApplication
public class BookPlatformServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookPlatformServerApplication.class, args);
    }
}
