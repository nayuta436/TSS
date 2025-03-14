package com.tss.connectionlayer.db_module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class TicketPriceDAO {
    public double getTicketPrice(String destinationCode, String ticketType, String seatType) {
        Connection connection = DatabaseConnector.getConnection();
        if (connection != null) {
            String sql = "SELECT price FROM ticket WHERE destination_code =? AND ticket_type =? AND seat_type =?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, destinationCode);
                statement.setString(2, ticketType);
                statement.setString(3, seatType);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getDouble("price");
                }
            } catch (SQLException e) {
                System.out.println("查询票价失败: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("关闭数据库连接失败: " + e.getMessage());
                }
            }
        }
        return 0.0;
    }
}
