package com.tss.interactionlayer.view_module;

import javax.swing.*;
import java.awt.*;

public class DisplayView extends JPanel {
    private JTextArea displayArea;

    public DisplayView() {
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));  // 统一字体
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
    }

    public void showMessage(String message) {
        displayArea.append(message + "\n");
    }
}
