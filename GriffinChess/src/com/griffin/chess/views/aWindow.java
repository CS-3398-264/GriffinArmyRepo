package com.griffin.chess.views;

import com.griffin.chess.utilities.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static java.awt.Color.*;

public abstract class aWindow extends JFrame implements ActionListener {
    static final int  BUTTON_Y = 400;
    static final int  LEFT_BUTTON_X = 20;
    static final int  RIGHT_BUTTON_X = 280;
    static final int  BUTTON_HEIGHT = 40;
    static final int  BUTTON_WIDTH = 100;

    GUI myGUI;
    HashMap<String, String> state;
    Color bgColor;
    Color textColor;

    aWindow(GUI parent, HashMap<String, String> state) {
        myGUI = parent;
        this.state = state;
        applyTheme();
        getContentPane().setBackground(bgColor);
        getContentPane().setForeground(textColor);

        /* aWindow Format/Size/Position settings */
        setSize(400, 500);
        setLocation(450, 100);

        /* Layout/Visibility aWindow Options */
        setLayout(null);
        setResizable(false);
    }

    void applyButtonTheme(JButton button) {

        if (state.get("theme").equals("night")) {
            button.setBackground(bgColor);
            button.setForeground(textColor);
            button.setOpaque(true);
            button.setBorderPainted(false);
        }
    }

    private void applyTheme() {
        if (state.get("theme").equals("night")) {
            bgColor = darkGray;
            textColor = lightGray;
        } else {
            bgColor = lightGray;
            textColor = black;
        }
    }

    public abstract void actionPerformed(ActionEvent e);
}
