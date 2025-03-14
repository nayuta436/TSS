package com.tss.servicelayer.ticket_module;

public class SeatType {
    private String name;
    private double priceAdjustment;

    public SeatType(String name, double priceAdjustment) {
        this.name = name;
        this.priceAdjustment = priceAdjustment;
    }

    public String getName() {
        return name;
    }

    public double getPriceAdjustment() {
        return priceAdjustment;
    }
}
