package com.tss.servicelayer.payment_module;

public class CardPayment extends PaymentMethod {
    @Override
    public boolean pay(double amount) {
        // 实际逻辑中处理MCard支付，这里简单返回成功
        System.out.println("MCard支付 " + amount + " 成功");
        return true;
    }
}
