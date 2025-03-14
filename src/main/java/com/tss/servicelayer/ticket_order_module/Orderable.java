package com.tss.servicelayer.ticket_order_module;

import java.util.List;

public interface Orderable {
    String createOrder(List<String> tickets);
    void cancelOrder(String orderId);
}
