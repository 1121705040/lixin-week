package week5.must1.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/06/ 06:48
 * @Presentation:
 */
@SpringBootApplication
public class ApplicationTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserBean user = (UserBean) context.getBean("user");
        System.out.println(user.getName());
    }
}
