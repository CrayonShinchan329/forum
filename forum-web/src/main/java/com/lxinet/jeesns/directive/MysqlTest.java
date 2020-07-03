package com.lxinet.jeesns.directive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlTest {


    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/forum?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password= "root";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, password);
        String sql = "select * from tbl_action";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }
    }
}
