package week5.must3;

import java.sql.*;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/06/ 22:12
 * @Presentation:
 */
public class JdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        String URL="jdbc:mysql://127.0.0.1:3306/java?useUnicode=true&characterEncoding=utf-8";
        String USER="root";
        String PASSWORD="lixin";
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据库链接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st=conn.createStatement();
        //增
//        st.execute("insert into user values (1,'中国')");
        //删
//        st.execute("delete from user where name='中国'");
        //改
//        st.execute("update user set id=2 where id=1");
        //查
        ResultSet rs=st.executeQuery("select * from user");

        //4.处理数据库的返回结果(使用ResultSet类)
        while(rs.next()){
            System.out.println(rs.getString("name")+" "
                    +rs.getString("id"));
        }

        //关闭资源
        rs.close();
        st.close();
        conn.close();
    }
}
