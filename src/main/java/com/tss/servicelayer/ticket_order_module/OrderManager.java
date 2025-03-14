package com.tss.servicelayer.ticket_order_module;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<TicketOrder> orders = new ArrayList<>();

    public String createOrder(List<String> tickets) {
        String orderId = "order_" + (orders.size() + 1);
        TicketOrder order = new TicketOrder(orderId);
        for (String ticket : tickets) {
            order.addTicket(ticket);
        }
        orders.add(order);
        return orderId;
    }

    public void cancelOrder(String orderId) {
        for (TicketOrder order : orders) {
            if (order.getOrderId().equals(orderId)) {
                orders.remove(order);
                break;
            }
        }
    }
}
