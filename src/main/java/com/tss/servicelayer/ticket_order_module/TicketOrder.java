package com.tss.servicelayer.ticket_order_module;

import java.util.ArrayList;
import java.util.List;

public class TicketOrder {
    private String orderId;
    private List<String> tickets;

    public TicketOrder(String orderId) {
        this.orderId = orderId;
        this.tickets = new ArrayList<>();
    }

    public String getOrderId() {
        return orderId;
    }

    public void addTicket(String ticket) {
        tickets.add(ticket);
    }

    public List<String> getTickets() {
        return tickets;
    }
}
