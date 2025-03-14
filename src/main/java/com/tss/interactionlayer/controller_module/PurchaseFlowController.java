package com.tss.interactionlayer.controller_module;

import com.tss.connectionlayer.db_module.TicketOrderDAO;
import com.tss.interactionlayer.view_module.DisplayView;
import com.tss.interactionlayer.view_module.InputView;
import com.tss.servicelayer.payment_module.CardPayment;
import com.tss.servicelayer.payment_module.CashPayment;
import com.tss.servicelayer.payment_module.PaymentMethod;
import com.tss.servicelayer.payment_module.PaymentProcessor;
import com.tss.servicelayer.ticket_module.SeatType;
import com.tss.servicelayer.ticket_module.TicketGenerator;
import com.tss.servicelayer.ticket_module.TicketInfoProvider;
import com.tss.servicelayer.ticket_module.TicketType;
import com.tss.servicelayer.ticket_order_module.OrderManager;
import com.tss.servicelayer.ticket_order_module.TicketOrder;

import java.util.ArrayList;
import java.util.List;

public class PurchaseFlowController {
    private String selectedTicketType;
    private String selectedSeatType;
    private TicketInfoProvider ticketInfoProvider;
    private PaymentProcessor paymentProcessor;
    private TicketGenerator ticketGenerator;
    private OrderManager orderManager;
    private DisplayView displayView;
    private InputView inputView;

    public PurchaseFlowController(DisplayView displayView, InputView inputView) {
        this.displayView = displayView;
        this.inputView = inputView;
        ticketInfoProvider = new TicketInfoProvider();
        paymentProcessor = new PaymentProcessor();
        ticketGenerator = new TicketGenerator();
        orderManager = new OrderManager();
    }

    public void setSelectedTicketType(String ticketType) {
        this.selectedTicketType = ticketType;
    }

    public void setSelectedSeatType(String seatType) {
        this.selectedSeatType = seatType;
    }

    public void continuePurchase(String destination, String ticketType, String seatType, int quantity) {
        TicketType selectedTicket = null;
        SeatType selectedSeat = null;

        for (TicketType t : ticketInfoProvider.getTicketTypes()) {
            if (t.getName().equals(ticketType)) {
                selectedTicket = t;
                break;
            }
        }

        for (SeatType s : ticketInfoProvider.getSeatTypes()) {
            if (s.getName().equals(seatType)) {
                selectedSeat = s;
                break;
            }
        }

        if (selectedTicket != null && selectedSeat != null) {
            double totalPrice = (selectedTicket.getPrice() + selectedSeat.getPriceAdjustment()) * quantity;
            displayView.showMessage("请确认支付金额：" + totalPrice + " 元");
            // 这里不进行支付操作，等待用户点击支付按钮
        } else {
            displayView.showMessage("车票或坐席类型选择有误，请重新选择。");
        }
    }

    public void handlePayment(String paymentMethod) {
        String destination = inputView.getDestinationCode();
        String ticketType = inputView.getSelectedTicketType();
        String seatType = inputView.getSelectedSeatType();
        int quantity = inputView.getSelectedTicketQuantity();

        TicketType selectedTicket = null;
        SeatType selectedSeat = null;

        for (TicketType t : ticketInfoProvider.getTicketTypes()) {
            if (t.getName().equals(ticketType)) {
                selectedTicket = t;
                break;
            }
        }

        for (SeatType s : ticketInfoProvider.getSeatTypes()) {
            if (s.getName().equals(seatType)) {
                selectedSeat = s;
                break;
            }
        }

        if (selectedTicket != null && selectedSeat != null) {
            double totalPrice = (selectedTicket.getPrice() + selectedSeat.getPriceAdjustment()) * quantity;
            PaymentMethod payment = null;
            if ("现金".equals(paymentMethod)) {
                payment = new CashPayment();
            } else if ("MCard".equals(paymentMethod)) {
                payment = new CardPayment();
            }

            if (payment != null && paymentProcessor.processPayment(payment, totalPrice)) {
                List<String> tickets = new ArrayList<>();
                for (int i = 0; i < quantity; i++) {
                    String ticket = ticketGenerator.generateTicket(selectedTicket, selectedSeat);
                    tickets.add(ticket);
                }
                String orderId = orderManager.createOrder(tickets);
                displayView.showMessage("订单创建成功，订单号: " + orderId);
                displayView.showMessage("车票已打印");

                // 保存订单到数据库
                TicketOrder order = new TicketOrder(orderId);
                order.getTickets().addAll(tickets);
                TicketOrderDAO orderDAO = new TicketOrderDAO();
                orderDAO.save(order);
            } else {
                displayView.showMessage("支付失败，请重新选择支付方式或检查支付信息。");
            }
        } else {
            displayView.showMessage("车票或坐席类型选择有误，请重新选择。");
        }
    }

    public void cancelPurchase() {
        // 实际逻辑中进行取消购票的相关处理
        System.out.println("执行取消购票操作");
    }
}
