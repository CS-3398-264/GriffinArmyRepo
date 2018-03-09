package com.griffin.chess.views;

import com.griffin.chess.utilities.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class WinnerWindow extends aWindow {
    public WinnerWindow(GUI parent, HashMap<String, String> state, String winningPlayer) {
        super(parent, state);
        setTitle("Game Over");

        /* Winner Message */
        JLabel message = new JLabel(winningPlayer + " wins!");
        message.setFont(new Font("Courier", Font.BOLD, 32));
        message.setBounds(60,80,300,100);
        message.setForeground(textColor);
        add(message);

        /* 'Start' and 'Options' Buttons */
        JButton menuButton = new JButton("Main Menu");
        menuButton.setBounds(LEFT_BUTTON_X + 120, BUTTON_Y, BUTTON_WIDTH + 10, BUTTON_HEIGHT);
        menuButton.addActionListener(this);
        applyButtonTheme(menuButton);
        add(menuButton);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String buttonType = e.getActionCommand();
        setVisible(false);
        switch (buttonType) {
            default:
                myGUI.setView("main");
                break;
        }
    }
}
