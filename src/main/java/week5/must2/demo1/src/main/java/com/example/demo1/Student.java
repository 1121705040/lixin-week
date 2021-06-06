package com.example.demo1;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "student")
public class Student{
    private String name = "qqqq";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
