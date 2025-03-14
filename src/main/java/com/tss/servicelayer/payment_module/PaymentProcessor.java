package com.tss.servicelayer.payment_module;

public class PaymentProcessor implements PaymentProcessorInterface {
    @Override
    public boolean processPayment(PaymentMethod method, double amount) {
        return method.pay(amount);
    }
}
