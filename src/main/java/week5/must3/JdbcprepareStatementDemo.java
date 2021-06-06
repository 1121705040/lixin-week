package week5.must3;

import java.sql.*;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/06/ 22:12
 * @Presentation:
 */
public class JdbcprepareStatementDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        String URL="jdbc:mysql://127.0.0.1:3306/java?useUnicode=true&characterEncoding=utf-8";
        String USER="root";
        String PASSWORD="lixin";
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得数据库链接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        //增
        PreparedStatement add = conn.prepareStatement("insert into user values (?,?)");
//        add.setObject(1,1);
//        add.setObject(2,"中国");
//        add.execute();
        //删
        PreparedStatement del = conn.prepareStatement("delete from user where name=?");
//        del.setObject(1,"中国");
//        del.execute();
        //改
        PreparedStatement upd = conn.prepareStatement("update user set id=? where id=?");
//        upd.setObject(1,1);
//        upd.setObject(2,2);
//        upd.execute();
        //查
        PreparedStatement sle = conn.prepareStatement("select * from user where id=?");
        sle.setObject(1,1);
        ResultSet rs = sle.executeQuery();

        //4.处理数据库的返回结果(使用ResultSet类)
        while(rs.next()){
            System.out.println(rs.getString("name")+" "
                    +rs.getString("id"));
        }

        //关闭资源
        rs.close();
        add.close();
        del.close();
        upd.close();
        sle.close();
        conn.close();
    }
}
