package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(Klass.class)
@EnableConfigurationProperties(Student.class)
public class School implements ISchool {

    @Autowired
    Klass class1;

    @Autowired
    Student student;

    @Override
    public void ding(){

        System.out.println("Class1 have " + this.class1.getStudents()+ " students and one is " + this.student);

    }

}
