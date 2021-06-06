package com.example.demo1;

import lombok.Data;

import java.util.List;

@Data
public class Klass {

    private String students;

    public void dong(){
        System.out.println(this.getStudents());
    }

}
