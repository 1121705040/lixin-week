package week7.must1;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/06/ 22:12
 * @Presentation:
 */
public class HikariDemoSql {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ExecutionException, InterruptedException {
        //这次加了批处理参数rewriteBatchedStatements=true,使用拼SQL的方式,加了4个线程,用时33秒
        String URL="jdbc:mysql://127.0.0.1:3306/java?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true";
        String USER="root";
        String PASSWORD="lixin";
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USER);
        hikariDataSource.setPassword(PASSWORD);
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setMaximumPoolSize(25);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);

        //增
        List<Object[]> list = new ArrayList<Object[]>();
        List<Object[]> list1 = new ArrayList<Object[]>();
        List<Object[]> list2 = new ArrayList<Object[]>();
        List<Object[]> list3 = new ArrayList<Object[]>();


        for(int i=0;i<250000;i++){
            String date = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            list.add(new Object[]{""+i,"1",date,date,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
        }
        for(int i=250000;i<500000;i++){
            String date = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            list1.add(new Object[]{""+i,"1",date,date,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
        }
        for(int i=500000;i<750000;i++){
            String date = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            list2.add(new Object[]{""+i,"1",date,date,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
        }
        for(int i=750000;i<1000000;i++){
            String date = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            list3.add(new Object[]{""+i,"1",date,date,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
        }
        StringBuilder sb = new StringBuilder("insert into sys_user_info values");
        for (Object[] obj:list) {
            sb.append(" (");
            for (int i=0;i<obj.length;i++) {
                if (i!=obj.length-1){
                    sb.append("'").append(obj[i]).append("'").append(",");
                }else {
                    sb.append(obj[i]);
                }
            }
            sb.append("),");
        }
        String sql = sb.toString();

        StringBuilder sb1 = new StringBuilder("insert into sys_user_info values");
        for (Object[] obj:list1) {
            sb1.append(" (");
            for (int i=0;i<obj.length;i++) {
                if (i!=obj.length-1){
                    sb1.append("'").append(obj[i]).append("'").append(",");
                }else {
                    sb1.append(obj[i]);
                }
            }
            sb1.append("),");
        }
        String sql1 = sb1.toString();

        StringBuilder sb2 = new StringBuilder("insert into sys_user_info values");
        for (Object[] obj:list2) {
            sb2.append(" (");
            for (int i=0;i<obj.length;i++) {
                if (i!=obj.length-1){
                    sb2.append("'").append(obj[i]).append("'").append(",");
                }else {
                    sb2.append(obj[i]);
                }
            }
            sb2.append("),");
        }
        String sql2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder("insert into sys_user_info values");
        for (Object[] obj:list3) {
            sb3.append(" (");
            for (int i=0;i<obj.length;i++) {
                if (i!=obj.length-1){
                    sb3.append("'").append(obj[i]).append("'").append(",");
                }else {
                    sb3.append(obj[i]);
                }
            }
            sb3.append("),");
        }
        String sql3 = sb3.toString();
        long l = System.currentTimeMillis();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,sql));
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,sql1));
        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,sql2));
        CompletableFuture<String> stringCompletableFuture3 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,sql3));
        String show = stringCompletableFuture.get();
        String show1 = stringCompletableFuture1.get();
        String show2 = stringCompletableFuture2.get();
        String show3 = stringCompletableFuture3.get();
        long l1 = System.currentTimeMillis();
        System.out.println(show);
        System.out.println(show1);
        System.out.println(show2);
        System.out.println(show3);
        System.out.println(l1-l);
        //关闭资源
        hikariDataSource.close();
    }
    public static String insetInto(JdbcTemplate jdbcTemplate,String sql){
        jdbcTemplate.batchUpdate(sql.substring(0,sql.length()-1));
        return "插入完毕";
    }
}
