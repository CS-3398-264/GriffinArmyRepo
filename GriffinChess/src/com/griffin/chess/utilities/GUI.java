package com.griffin.chess.utilities;

import com.griffin.chess.containers.App;
import com.griffin.chess.views.BoardWindow;
import com.griffin.chess.views.MenuWindow;
import com.griffin.chess.views.OptionsWindow;
import com.griffin.chess.views.WinnerWindow;

import java.util.HashMap;

public class GUI {
    private App myApp;
    private String currentView;

    public GUI (App app) {
        myApp = app;
        currentView = "";
    }

    public void updateView(HashMap<String, String> newState) {
        String newView = newState.get("view");
        if (!newView.equals(currentView)) {
            if (newView.equals("options")) new OptionsWindow(this, newState);
            if (newView.equals("main")) new MenuWindow(this, newState);
            if (newView.equals("board")) myApp.startNewGame(new BoardWindow(this, newState));
        }
    }

    public void setWinner(HashMap<String, String> newState, String winningPlayer) {
        new WinnerWindow(this, newState, winningPlayer);
    }

    public void setView(String newView) {
        myApp.setView(newView);
    }

    public void setOptions(HashMap<String, String> selectedOptions) {
        myApp.setOptions(selectedOptions);
    }

    public void setSelected(String selected) {
        // sets the selected Cell
        myApp.setSelected(selected);
    }

    public void confirmMove() {
        myApp.confirmMove();
    }
}