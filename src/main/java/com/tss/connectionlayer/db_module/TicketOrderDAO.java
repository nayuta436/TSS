package com.tss.connectionlayer.db_module;

import com.tss.servicelayer.ticket_order_module.TicketOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketOrderDAO implements DAO<TicketOrder> {
    @Override
    public void save(TicketOrder object) {
        Connection connection = DatabaseConnector.getConnection();
        if (connection != null) {
            String sql = "INSERT INTO ticket (destination_code, destination, ticket_type, seat_type, price, order_id) " +
                    "VALUES (?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (String ticket : object.getTickets()) {
                    // 假设 ticket 是一个包含所有信息的字符串，这里需要根据实际格式解析
                    // 例如："目的地代码,目的地,车票类型,座位类型,票价"
                    String[] parts = ticket.split(",");
                    statement.setString(1, parts[0]);
                    statement.setString(2, parts[1]);
                    statement.setString(3, parts[2]);
                    statement.setString(4, parts[3]);
                    statement.setDouble(5, Double.parseDouble(parts[4]));
                    statement.setString(6, object.getOrderId());
                    statement.executeUpdate();
                }
                System.out.println("保存订单到数据库: " + object.getOrderId());
            } catch (SQLException e) {
                System.out.println("保存订单失败: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("关闭数据库连接失败: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public TicketOrder findById(String id) {
        Connection connection = DatabaseConnector.getConnection();
        if (connection != null) {
            String sql = "SELECT * FROM ticket WHERE order_id =?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();
                TicketOrder order = new TicketOrder(id);
                while (resultSet.next()) {
                    String ticketInfo = resultSet.getString("destination_code") + "," +
                            resultSet.getString("destination") + "," +
                            resultSet.getString("ticket_type") + "," +
                            resultSet.getString("seat_type") + "," +
                            resultSet.getString("price");
                    order.getTickets().add(ticketInfo);
                }
                return order;
            } catch (SQLException e) {
                System.out.println("查询订单失败: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("关闭数据库连接失败: " + e.getMessage());
                }
            }
        }
        return null;
    }
}
