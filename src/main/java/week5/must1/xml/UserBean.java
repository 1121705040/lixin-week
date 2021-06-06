package week5.must1.xml;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/06/ 06:44
 * @Presentation:
 */
@Data
@Component
public class UserBean {
    private String name;
    private String age;
    private String sex;
}
