package week7.must1;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/06/ 22:12
 * @Presentation:
 */
public class HikariDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        //共用时1814700毫秒
        String URL="jdbc:mysql://127.0.0.1:3306/java?useUnicode=true&characterEncoding=utf-8";
        String USER="root";
        String PASSWORD="lixin";
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USER);
        hikariDataSource.setPassword(PASSWORD);
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setMaximumPoolSize(8);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);

        //增
        List<Object[]> list = new ArrayList<Object[]>();
        for(int i=0;i<1000000;i++){
            String date = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            list.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
        }
        long l = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("insert into sys_user_info values (?,?,?,?,?,?,?,?,?,?,?,?,?)",list);
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);

        //关闭资源
        hikariDataSource.close();
    }
}
