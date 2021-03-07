package com.nhz.mycode.completed.arthas;

import com.nhz.mycode.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HotCompileLab   {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping(value = "/get/{id}")
    public User get(@PathVariable Integer id){
        if (id != null && id < 1) {
            throw new IllegalArgumentException("id < 1");
        }
        return User.builder().userId(id.toString()).userName("name:"+id).build();
    }
}
