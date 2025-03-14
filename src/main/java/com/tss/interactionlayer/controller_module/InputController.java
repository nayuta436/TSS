package com.tss.interactionlayer.controller_module;

import com.tss.interactionlayer.view_module.DisplayView;
import com.tss.interactionlayer.view_module.InputView;

public class InputController {
    private PurchaseFlowController flowController;
    private DisplayView displayView;
    private InputView inputView;

    public InputController(PurchaseFlowController flowController, DisplayView displayView, InputView inputView) {
        this.flowController = flowController;
        this.displayView = displayView;
        this.inputView = inputView;
    }

    public void handleTicketTypeSelection(String ticketType) {
        displayView.showMessage("已选择: " + ticketType);
        displayView.showMessage("请选择坐席类型。");
        flowController.setSelectedTicketType(ticketType);
    }

    public void handleSeatTypeSelection(String seatType) {
        displayView.showMessage("已选择: " + seatType);
        displayView.showMessage("请选择车票数量。");
        flowController.setSelectedSeatType(seatType);
    }

    public void handleContinue() {
        String destination = inputView.getDestinationCode();
        String ticketType = inputView.getSelectedTicketType();
        String seatType = inputView.getSelectedSeatType();
        int quantity = inputView.getSelectedTicketQuantity();

        if (destination != null && ticketType != null && seatType != null) {
            displayView.showMessage("正在处理您的购票请求，请稍候...");
            flowController.continuePurchase(destination, ticketType, seatType, quantity);
        } else {
            displayView.showMessage("请完整选择各项信息");
        }
    }

    public void handleCancel() {
        displayView.showMessage("取消购票操作");
        flowController.cancelPurchase();
    }

    public void handleCashPayment() {
        flowController.handlePayment("现金");
    }

    public void handleCardPayment() {
        flowController.handlePayment("MCard");
    }
}
