package ru.shashkov.view;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Display {
    private static final int DISPLAY_ELEMENT_WIDTH = 50;
    private static final int DISPLAY_ELEMENT_HEIGHT = 30;
    private static final Dimension displayElementSize = new Dimension(DISPLAY_ELEMENT_WIDTH, DISPLAY_ELEMENT_HEIGHT);
    private final ListenerCreator listenerCreator;
    private JButton getResultButton;

    @Getter
    private JPanel displayPanel;

    Display(ListenerCreator listenerCreator) {
        this.listenerCreator = listenerCreator;
        displayPanel = new JPanel();
        createDisplay();
    }

    private void createDisplay() {
        getResultButton = new JButton();
        getResultButton.setContentAreaFilled(false);
        getResultButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        getResultButton.setPreferredSize(displayElementSize);
        getResultButton.setHorizontalAlignment(SwingConstants.CENTER);
        getResultButton.setFocusPainted(false);
        getResultButton.addActionListener(listenerCreator.createListenerForRestartButton());
    }
}
