package com.griffin.chess.containers;

import com.griffin.chess.players.Human;
import com.griffin.chess.utilities.GUI;
import com.griffin.chess.views.BoardWindow;

import java.util.HashMap;

public class App {
    private HashMap<String, String> options;
    private Board myBoard;
    private GUI myGUI;

    public App() {
        options = new HashMap<>();
        myGUI = new GUI(this);
        setInitialOptions();
    }

    private void setInitialOptions() {
        options.put("view", "main");
        options.put("theme", "day");
        options.put("opponent", "robot");
        options.put("difficulty", "normal");
        myGUI.updateView(options);
    }

    public HashMap<String, String> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, String> newState) {
        options = newState;
        myGUI.updateView(options);
    }

    public void setSelected(String selected) {
        myBoard.setSelected(selected);
    }

    public void startNewGame(BoardWindow gameScreen) {
        myBoard = new Board(options, new Human(0));
        myBoard.addObserver(gameScreen);
        myBoard.updateDisplay();
    }

    public void setView(String newView) {
        options.put("view", newView);
        myGUI.updateView(options);
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public GUI getMyGUI() {
        return myGUI;
    }

    public void confirmMove() {
        myBoard.confirmMove();
    }
}
