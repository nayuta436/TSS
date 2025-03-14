package com.tss;

import com.tss.interactionlayer.controller_module.InputController;
import com.tss.interactionlayer.controller_module.PurchaseFlowController;
import com.tss.interactionlayer.view_module.CombinedView;
import com.tss.interactionlayer.view_module.DisplayView;
import com.tss.interactionlayer.view_module.InputView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DisplayView displayView = new DisplayView();
                InputView inputView = new InputView(null);
                PurchaseFlowController flowController = new PurchaseFlowController(displayView, inputView);
                InputController inputController = new InputController(flowController, displayView, inputView);
                inputView.setInputController(inputController);

                // 使用 CombinedView 来显示合并后的窗口
                CombinedView combinedView = new CombinedView(inputController);
            }
        });
    }
}
