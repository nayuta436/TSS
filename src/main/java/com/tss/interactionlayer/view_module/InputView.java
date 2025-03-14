package com.tss.interactionlayer.view_module;

import com.tss.interactionlayer.controller_module.InputController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputView extends JPanel {
    private JTextField destinationField;
    private JButton[] ticketTypeButtons;
    private JButton[] seatTypeButtons;
    private JSpinner ticketQuantitySpinner;
    private JButton continueButton, cancelButton;
    private JButton cashPaymentButton, cardPaymentButton;
    private InputController inputController;

    public InputView(InputController inputController) {
        this.inputController = inputController;
        setLayout(new BorderLayout());

        // 操作说明区域
        JTextArea operationInstructions = new JTextArea("一些操作说明内容可在此展示");
        operationInstructions.setEditable(false);
        operationInstructions.setFont(new Font("Arial", Font.PLAIN, 14));  // 统一字体
        operationInstructions.setLineWrap(true);
        operationInstructions.setWrapStyleWord(true);
        add(operationInstructions, BorderLayout.NORTH);

        // 中间面板，使用 GridBagLayout 布局
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // 增加组件间距

        // 目的地键盘区域
        JPanel destinationPanel = new JPanel();
        destinationPanel.add(new JLabel("请输入目的地代码: "));
        destinationField = new JTextField(20);
        destinationPanel.add(destinationField);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(destinationPanel, gbc);

        // 创建 9 键数字键盘
        JPanel numberPadPanel = new JPanel(new GridLayout(3, 3));
        String[] numberLabels = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (String label : numberLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String currentText = destinationField.getText();
                    destinationField.setText(currentText + label);
                }
            });
            numberPadPanel.add(button);
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(numberPadPanel, gbc);

        // 车票键盘区域
        JPanel ticketPanel = new JPanel(new GridLayout(4, 3));
        String[] ticketLabels = {"单程票", "多次往返票"};
        ticketTypeButtons = new JButton[ticketLabels.length];
        for (int i = 0; i < ticketLabels.length; i++) {
            ticketTypeButtons[i] = new JButton(ticketLabels[i]);
            ticketTypeButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inputController.handleTicketTypeSelection(((JButton) e.getSource()).getText());
                }
            });
            ticketPanel.add(ticketTypeButtons[i]);
        }
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(ticketPanel, gbc);

        // 坐席类型键盘区域
        JPanel seatPanel = new JPanel(new GridLayout(1, 3));
        String[] seatLabels = {"普通座", "商务座", "一等座"};
        seatTypeButtons = new JButton[seatLabels.length];
        for (int i = 0; i < seatLabels.length; i++) {
            seatTypeButtons[i] = new JButton(seatLabels[i]);
            seatTypeButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inputController.handleSeatTypeSelection(((JButton) e.getSource()).getText());
                }
            });
            seatPanel.add(seatTypeButtons[i]);
        }
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(seatPanel, gbc);

        // 车票数量选择区域
        JPanel quantityPanel = new JPanel();
        quantityPanel.add(new JLabel("选择车票数量: "));
        ticketQuantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantityPanel.add(ticketQuantitySpinner);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(quantityPanel, gbc);

        // 支付方式区域
        JPanel paymentPanel = new JPanel();
        cashPaymentButton = new JButton("现金支付");
        cardPaymentButton = new JButton("MCard支付");
        cashPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputController.handleCashPayment();
            }
        });
        cardPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputController.handleCardPayment();
            }
        });
        paymentPanel.add(cashPaymentButton);
        paymentPanel.add(cardPaymentButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(paymentPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // 继续/取消键盘区域
        JPanel operationButtonPanel = new JPanel();
        continueButton = new JButton("继续");
        cancelButton = new JButton("取消");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputController.handleContinue();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputController.handleCancel();
            }
        });
        operationButtonPanel.add(continueButton);
        operationButtonPanel.add(cancelButton);
        add(operationButtonPanel, BorderLayout.SOUTH);
    }

    public String getDestinationCode() {
        return destinationField.getText();
    }

    public String getSelectedTicketType() {
        for (JButton button : ticketTypeButtons) {
            if (button.getBackground() == Color.LIGHT_GRAY) {
                return button.getText();
            }
        }
        return null;
    }

    public String getSelectedSeatType() {
        for (JButton button : seatTypeButtons) {
            if (button.getBackground() == Color.LIGHT_GRAY) {
                return button.getText();
            }
        }
        return null;
    }

    public int getSelectedTicketQuantity() {
        return (int) ticketQuantitySpinner.getValue();
    }

    // 在 InputView 类中添加以下方法
    public void setInputController(InputController inputController) {
        this.inputController = inputController;
    }
}