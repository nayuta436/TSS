package com.tss.InteractionLayer.view_module;

import javax.swing.*;
import java.awt.*;

public class display {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 创建主窗口
            JFrame frame = new JFrame("售票系统");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);

            // 使用 BorderLayout 布局管理器将窗口分为上下两部分
            frame.setLayout(new BorderLayout());

            // 上部分面板
            JPanel upperPanel = new JPanel(new BorderLayout());

            // 上部分左半部分面板
            JPanel leftPanel = new JPanel(new BorderLayout());
            JLabel operationLabel = new JLabel("一些操作说明");
            JButton printerButton = new JButton("打印机");
            leftPanel.add(operationLabel, BorderLayout.NORTH);
            leftPanel.add(printerButton, BorderLayout.SOUTH);

            // 上部分右半部分面板（显示屏）
            JPanel rightPanel = new JPanel();
            rightPanel.setBackground(Color.LIGHT_GRAY);
            JLabel displayLabel = new JLabel("显示屏");
            rightPanel.add(displayLabel);

            // 使用 JSplitPane 来实现左右部分宽度比为 1:3
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
            splitPane.setDividerLocation(frame.getWidth() / 4);
            splitPane.setResizeWeight(0.25);

            upperPanel.add(splitPane, BorderLayout.CENTER);

            // 下部分面板
            JPanel lowerPanel = new JPanel(new BorderLayout());
            // 设置下部分面板的内边距
            lowerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // 下部分左部分：数字键盘
            JPanel leftLowerPanel = new JPanel(new GridLayout(4, 3, 5, 5));
            leftLowerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            for (int i = 0; i < 10; i++) {
                JButton numButton = new JButton(String.valueOf(i));
                leftLowerPanel.add(numButton);
            }
            JButton clearButton = new JButton("C");
            JButton enterButton = new JButton("Enter");
            leftLowerPanel.add(clearButton);
            leftLowerPanel.add(enterButton);

            // 下部分中部分
            JPanel middleLowerPanel = new JPanel(new BorderLayout());
            middleLowerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JPanel middleUpperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JButton cancelButton = new JButton("取消");
            JButton continueButton = new JButton("继续");
            middleUpperPanel.add(cancelButton);
            middleUpperPanel.add(continueButton);

            JPanel middleLowerSubPanel = new JPanel(new GridLayout(5, 2, 5, 5));
            middleLowerSubPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            String[] middleButtonsText = {"一等座", "二等座", "刷卡支付", "现金支付","车票1", "车票2", "车票3", "车票4"};
            for (String text : middleButtonsText) {
                JButton button = new JButton(text);
                middleLowerSubPanel.add(button);
            }

            middleLowerPanel.add(middleUpperPanel, BorderLayout.NORTH);
            middleLowerPanel.add(middleLowerSubPanel, BorderLayout.CENTER);

            // 下部分右部分
            JPanel rightLowerPanel = new JPanel(new GridLayout(3, 1, 5, 5));
            rightLowerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            String[] rightButtonsText = {"刷卡口", "硬币口", "纸币槽"};
            for (String text : rightButtonsText) {
                JButton button = new JButton(text);
                rightLowerPanel.add(button);
            }

            lowerPanel.add(leftLowerPanel, BorderLayout.WEST);
            lowerPanel.add(middleLowerPanel, BorderLayout.CENTER);
            lowerPanel.add(rightLowerPanel, BorderLayout.EAST);

            // 创建一个新的 JSplitPane 来划分上下部分，使上下部分占比为 1:1
            JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPanel, lowerPanel);
            verticalSplitPane.setDividerLocation(frame.getHeight() / 2);
            verticalSplitPane.setResizeWeight(0.5);

            frame.add(verticalSplitPane, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
