package week7.must1;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/06/ 22:12
 * @Presentation:
 */
public class HikariDemoThread {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ExecutionException, InterruptedException {
        //这次加了批处理参数rewriteBatchedStatements=true加了20个线程,用时26秒
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
        List<Object[]> list4 = new ArrayList<Object[]>();
        List<Object[]> list5 = new ArrayList<Object[]>();
        List<Object[]> list6 = new ArrayList<Object[]>();
        List<Object[]> list7 = new ArrayList<Object[]>();
        List<Object[]> list8 = new ArrayList<Object[]>();
        List<Object[]> list9 = new ArrayList<Object[]>();
        List<Object[]> list10 = new ArrayList<Object[]>();
        List<Object[]> list11 = new ArrayList<Object[]>();
        List<Object[]> list12 = new ArrayList<Object[]>();
        List<Object[]> list13 = new ArrayList<Object[]>();
        List<Object[]> list14 = new ArrayList<Object[]>();
        List<Object[]> list15 = new ArrayList<Object[]>();
        List<Object[]> list16 = new ArrayList<Object[]>();
        List<Object[]> list17 = new ArrayList<Object[]>();
        List<Object[]> list18 = new ArrayList<Object[]>();
        List<Object[]> list19 = new ArrayList<Object[]>();


        for(int i=0;i<1000000;i++){
            String date = new java.sql.Timestamp(System.currentTimeMillis()).toString();
            if (i<50000){
                list.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<100000){
                list1.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<150000){
                list2.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<200000){
                list3.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<250000){
                list4.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<300000){
                list5.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<350000){
                list6.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<400000){
                list7.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<450000){
                list8.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<500000){
                list9.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<550000){
                list10.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<600000){
                list11.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<650000){
                list12.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<700000){
                list13.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<750000){
                list14.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<800000){
                list15.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<850000){
                list16.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<900000){
                list17.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<950000){
                list18.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
            if (i<1000000){
                list19.add(new Object[]{""+i,"1",date,null,"lixin123","lixin","123","/root","xx.com","男","123","183xxx9832",12.32D});
                continue;
            }
        }
        long l = System.currentTimeMillis();
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list));
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list1));
        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list2));
        CompletableFuture<String> stringCompletableFuture3 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list3));
        CompletableFuture<String> stringCompletableFuture4 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list4));
        CompletableFuture<String> stringCompletableFuture5 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list5));
        CompletableFuture<String> stringCompletableFuture6 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list6));
        CompletableFuture<String> stringCompletableFuture7 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list7));
        CompletableFuture<String> stringCompletableFuture8 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list8));
        CompletableFuture<String> stringCompletableFuture9 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list9));
        CompletableFuture<String> stringCompletableFuture10 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list10));
        CompletableFuture<String> stringCompletableFuture11 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list11));
        CompletableFuture<String> stringCompletableFuture12 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list12));
        CompletableFuture<String> stringCompletableFuture13 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list13));
        CompletableFuture<String> stringCompletableFuture14 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list14));
        CompletableFuture<String> stringCompletableFuture15 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list15));
        CompletableFuture<String> stringCompletableFuture16 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list16));
        CompletableFuture<String> stringCompletableFuture17 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list17));
        CompletableFuture<String> stringCompletableFuture18 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list18));
        CompletableFuture<String> stringCompletableFuture19 = CompletableFuture.supplyAsync(() -> insetInto(jdbcTemplate,list19));

        String show = stringCompletableFuture.get();
        String show1 = stringCompletableFuture1.get();
        String show2 = stringCompletableFuture2.get();
        String show3 = stringCompletableFuture3.get();
        String show4 = stringCompletableFuture4.get();
        String show5 = stringCompletableFuture5.get();
        String show6 = stringCompletableFuture6.get();
        String show7 = stringCompletableFuture7.get();
        String show8 = stringCompletableFuture8.get();
        String show9 = stringCompletableFuture9.get();
        String show10 = stringCompletableFuture10.get();
        String show11 = stringCompletableFuture11.get();
        String show12 = stringCompletableFuture12.get();
        String show13 = stringCompletableFuture13.get();
        String show14 = stringCompletableFuture14.get();
        String show15 = stringCompletableFuture15.get();
        String show16 = stringCompletableFuture16.get();
        String show17 = stringCompletableFuture17.get();
        String show18 = stringCompletableFuture18.get();
        String show19 = stringCompletableFuture19.get();
        long l1 = System.currentTimeMillis();
        System.out.println(show);
        System.out.println(show1);
        System.out.println(show2);
        System.out.println(show3);
        System.out.println(show4);
        System.out.println(show5);
        System.out.println(show6);
        System.out.println(show7);
        System.out.println(show8);
        System.out.println(show9);
        System.out.println(show10);
        System.out.println(show11);
        System.out.println(show12);
        System.out.println(show13);
        System.out.println(show14);
        System.out.println(show15);
        System.out.println(show16);
        System.out.println(show17);
        System.out.println(show18);
        System.out.println(show19);
        System.out.println(l1-l);
        //关闭资源
        hikariDataSource.close();
    }
    public static String insetInto(JdbcTemplate jdbcTemplate,List list){
        jdbcTemplate.batchUpdate("insert into sys_user_info values (?,?,?,?,?,?,?,?,?,?,?,?,?)",list);
        return "插入完毕";
    }
}
