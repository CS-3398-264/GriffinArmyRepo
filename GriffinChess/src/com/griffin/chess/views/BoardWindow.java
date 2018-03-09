package com.griffin.chess.views;

import com.griffin.chess.utilities.Cell;
import com.griffin.chess.utilities.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class BoardWindow extends aWindow implements Observer {
    private String theme;
    private ArrayList<ArrayList<Cell>> boardUICells;

    public BoardWindow(GUI parent, HashMap<String, String> state) {
        super(parent, state);
        setTitle("Griffin Chess");
        theme = state.get("theme");
        boardUICells = generateBlankCells();

        /* 'Quit' and 'Take Turn' Buttons */
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(LEFT_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.addActionListener(this);
        applyButtonTheme(quitButton);
        add(quitButton);
        JButton takeTurnButton = new JButton("Confirm");
        takeTurnButton.setBounds(RIGHT_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        takeTurnButton.addActionListener(this);
        applyButtonTheme(takeTurnButton);
        add(takeTurnButton);
        setVisible(true);
    }

    private ArrayList<ArrayList<Cell>> generateBlankCells() {
        boardUICells = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            boardUICells.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                boardUICells.get(row).add(new Cell(this));
            }
        }
        return boardUICells;
    }

    private void displayBoard(ArrayList<ArrayList<String>> boardState) {
        GridLayout gridLayout = new GridLayout(8,8);
        JPanel boardUI = new JPanel(gridLayout);
        // this should be in constructor or something.. not here
        int boardMargin = 20;
        int boardSize = 360;

        boardUI.setBounds(boardMargin, boardMargin, boardSize, boardSize);
        add(boardUI);

        Cell cell;
        for (int row=0;row < 8; row++) {
            for (int col=0; col < 8; col++) {
                String cellState = boardState.get(row).get(col);
                cell = boardUICells.get(row).get(col);
                cell.setPosition(row, col);
                cell.setCellGraphics(theme, cellState);
                boardUI.add(cell);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String buttonType = e.getActionCommand();
        switch (buttonType) {
            case "Quit":
                setVisible(false);
                myGUI.setView("main");
                break;
            case "Confirm":
                myGUI.confirmMove();
                break;
            default:
                myGUI.setSelected(buttonType);
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<ArrayList<String>> boardState = ( ArrayList<ArrayList<String>> ) arg;
        if (boardState.get(0).get(1).equals("-")) {
            setVisible(false);
            String winningPlayer = boardState.get(0).get(0);
            myGUI.setWinner(state, winningPlayer);
        } else {
            displayBoard(boardState);
        }
    }
}
