package week7.must2.service;

import week7.must2.DataSourceMaster;
import week7.must2.DataSourceMy;
import week7.must2.DataSourceSlave;

import java.awt.*;

/**
 * @Author: LiXin
 * @CreateTime: 2021/06/22/ 19:27
 * @Presentation:
 */
public class UserService {
    public List getUser(){
        DataSourceMy dataSource = new DataSourceMaster();
        return null;
    }
    public String addUser(){
        DataSourceMy dataSource = new DataSourceSlave();
        return null;
    }
}
