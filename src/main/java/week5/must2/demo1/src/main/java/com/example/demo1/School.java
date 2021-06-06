package com.example.demo1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;


@Configuration
@Import(Klass.class)
@EnableAutoConfiguration(Student.class)
public class School implements ISchool {

    @Autowired
    Klass class1;

    @Autowired
    Student student;

    @Override
    public void ding(){

        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student);

    }

}
