package week7.must2;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/22/ 19:23
 * @Presentation:
 */
public class DataSourceMaster implements DataSourceMy {
    @Override
    public HikariDataSource getDataSource() {
        String URL="jdbc:mysql://127.0.0.1:3306/master?useUnicode=true&characterEncoding=utf-8";
        String USER="root";
        String PASSWORD="lixin";
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USER);
        hikariDataSource.setPassword(PASSWORD);
        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        hikariDataSource.setMaximumPoolSize(8);
        return hikariDataSource;
    }
}
