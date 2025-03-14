package com.tss.servicelayer.ticket_module;

public class TicketType {
    private String name;
    private double price;

    public TicketType(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
