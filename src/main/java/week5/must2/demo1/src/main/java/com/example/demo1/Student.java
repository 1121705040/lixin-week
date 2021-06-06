package week5.must2.demo1.src.main.java.com.example.demo1;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "student.name")
public class Student{
    private String name = "qqqq";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
