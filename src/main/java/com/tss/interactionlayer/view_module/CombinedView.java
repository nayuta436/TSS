package com.tss.interactionlayer.view_module;

import com.tss.interactionlayer.controller_module.InputController;

import javax.swing.*;

public class CombinedView extends JFrame {
    private InputView inputView;
    private DisplayView displayView;

    public CombinedView(InputController inputController) {
        setTitle("售票系统");
        setSize(800, 600);  // 调整窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // 窗口居中显示

        inputView = new InputView(inputController);
        displayView = new DisplayView();

        // 使用 JSplitPane 来分割输入和输出区域
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputView, displayView);
        splitPane.setDividerLocation(400);  // 设置分割线位置
        add(splitPane);

        setVisible(true);
    }

    public DisplayView getDisplayView() {
        return displayView;
    }

    public InputView getInputView() {
        return inputView;
    }
}
