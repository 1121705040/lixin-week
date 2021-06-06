package week5.must3;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/06/ 22:12
 * @Presentation:
 */
public class HikariDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
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
//        List<Object[]> list = new ArrayList<Object[]>();
//        list.add(new Object[]{2,"tom1"});
//        list.add(new Object[]{3,"tom2"});
//        list.add(new Object[]{4,"tom3"});
//        jdbcTemplate.batchUpdate("insert into user values (?,?)",list);

        //删
//        List<Object[]> list2 = new ArrayList<Object[]>();
//        list2.add(new Object[]{"tom1"});
//        list2.add(new Object[]{"tom2"});
//        list2.add(new Object[]{"tom3"});
//        jdbcTemplate.batchUpdate("delete from user where name=?",list2);

        //改
//        List<Object[]> list3 = new ArrayList<Object[]>();
//        list3.add(new Object[]{"李云龙","李鑫"});
//        jdbcTemplate.batchUpdate("update user set name=? where name=?",list3);

        //查
        List<Map<String, Object>> list4 = jdbcTemplate.queryForList("select * from user");

        for (Map<String,Object> map:list4) {
            System.out.println(map.get("id")+"==="+map.get("name"));
        }

        //关闭资源
        hikariDataSource.close();
    }
}
