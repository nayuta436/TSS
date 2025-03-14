package com.tss.servicelayer.payment_module;

public interface PaymentProcessorInterface {
    boolean processPayment(PaymentMethod method, double amount);
}
