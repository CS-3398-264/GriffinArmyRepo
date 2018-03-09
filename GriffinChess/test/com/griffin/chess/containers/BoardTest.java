package com.griffin.chess.containers;

import com.griffin.chess.players.Human;
import com.griffin.chess.views.BoardWindow;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardTest {

    @Test
    public void testPieceSelectionWithHighlightedMoves() {
        HashMap<String, String> initialOptions = new HashMap<>();
        initialOptions.put("view", "main");
        initialOptions.put("theme", "day");
        initialOptions.put("opponent", "robot");
        initialOptions.put("difficulty", "normal");
        App myApp = new App();
        myApp.startNewGame(new BoardWindow(myApp.getMyGUI(), initialOptions));

        myApp.setSelected("60");
        ArrayList<ArrayList<String>> boardState = myApp.getMyBoard().getBoardState();
        // check selected cell
        String selectedCell = boardState.get(6).get(0);
        Assert.assertEquals("selected cell is marked", "0♙00~", selectedCell);
        Assert.assertNotEquals("selected cell not unmarked", "0♙00", selectedCell);
        // check first move cell
        String firstMoveCell = boardState.get(5).get(0);
        Assert.assertEquals("available move 1 is marked", "-.", firstMoveCell);
        Assert.assertNotEquals("available move 1 not unmarked", "-", firstMoveCell);
        // check second move cell
        String secondMoveCell = boardState.get(4).get(0);
        Assert.assertEquals("available move 2 is marked", "-.", secondMoveCell);
        Assert.assertNotEquals("available move 2 not unmarked", "-", secondMoveCell);
        // check third, non-move cell
        String nonMoveCell = boardState.get(3).get(0);
        Assert.assertEquals("unavailable cell is unmarked", "-", nonMoveCell);
        Assert.assertNotEquals("unavailable cell is not marked", "-.", nonMoveCell);
    }

    @Test
    public void testAIMoveCompletion() {
        HashMap<String, String> initialOptions = new HashMap<>();
        initialOptions.put("view", "main");
        initialOptions.put("theme", "day");
        initialOptions.put("opponent", "robot");
        initialOptions.put("difficulty", "normal");
        App myApp = new App();
        myApp.startNewGame(new BoardWindow(myApp.getMyGUI(), initialOptions));
        ArrayList<ArrayList<String>> preTurnBoardState = myApp.getMyBoard().getBoardState();
        myApp.getMyBoard().confirmMove();
        ArrayList<ArrayList<String>> postTurnBoardState = myApp.getMyBoard().getBoardState();
        Assert.assertNotEquals("board has changed after AI takes a turn", preTurnBoardState, postTurnBoardState);
    }
}