package com.tss.servicelayer.ticket_module;

public class TicketGenerator {
    public String generateTicket(TicketType ticketType, SeatType seatType) {
        // 实际逻辑中生成车票字符串，可能包含更多信息
        return "生成的车票，类型: " + ticketType.getName() + ", 坐席: " + seatType.getName();
    }
}
