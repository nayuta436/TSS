package com.tss.connectionlayer.db_module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name"; // 替换为你的数据库名称
    private static final String USER = "root"; // 替换为你的用户名
    private static final String PASSWORD = "314159@Zyh"; // 替换为你的密码

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("获取数据库连接成功");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("获取数据库连接失败: " + e.getMessage());
        }
        return connection;
    }
}
