package com.tss.servicelayer.payment_module;

public class CashPayment extends PaymentMethod {
    @Override
    public boolean pay(double amount) {
        // 实际逻辑中处理现金支付，这里简单返回成功
        System.out.println("现金支付 " + amount + " 成功");
        return true;
    }
}
