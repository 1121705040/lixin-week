package week5.must2.demo1.src.main.java.com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Autowired
    private ISchool iSchool;

    @Bean
    public void schoolInfo(){
        iSchool.ding();
    }
}
