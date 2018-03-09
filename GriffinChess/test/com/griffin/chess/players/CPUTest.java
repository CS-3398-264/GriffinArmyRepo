package com.griffin.chess.players;

import com.griffin.chess.containers.App;
import com.griffin.chess.views.BoardWindow;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class CPUTest {

    @Test
    public void testNewGamePieceEval() {
        HashMap<String, String> initialOptions = new HashMap<>();
        initialOptions.put("view", "main");
        initialOptions.put("theme", "day");
        initialOptions.put("opponent", "robot");
        initialOptions.put("difficulty", "hard");
        App myApp = new App();
        myApp.startNewGame(new BoardWindow(myApp.getMyGUI(), initialOptions));
        ArrayList<ArrayList<String>> initialBoardState = myApp.getMyBoard().getBoardState();

        CPU robotPlayer = ( CPU ) myApp.getMyBoard().getPlayers().get(1);
        int boardPieceScore = robotPlayer.calculateBoardScore(initialBoardState, false);
        Assert.assertEquals("Piece Evaluation should be equal to 0 on new board", 0, boardPieceScore);
        int preMoveScore = robotPlayer.calculateBoardScore(initialBoardState, true);
        Assert.assertNotEquals("Position Evaluation should not equal 0 on new board", 0, preMoveScore);
        myApp.getMyBoard().notifyAI(robotPlayer);
        myApp.getMyBoard().notifyAI(robotPlayer);
        ArrayList<ArrayList<String>> newBoardState = myApp.getMyBoard().getBoardState();
        int postMoveScore = robotPlayer.calculateBoardScore(newBoardState, true);
        Assert.assertNotEquals("After two moves, position Evaluation should have changed", preMoveScore, postMoveScore);
    }

    @Test
    public void testNewGamePositionEval() {
        HashMap<String, String> initialOptions = new HashMap<>();
        initialOptions.put("view", "main");
        initialOptions.put("theme", "day");
        initialOptions.put("opponent", "robot");
        initialOptions.put("difficulty", "hard");
        App myApp = new App();
        myApp.startNewGame(new BoardWindow(myApp.getMyGUI(), initialOptions));
        ArrayList<ArrayList<String>> initialBoardState = myApp.getMyBoard().getBoardState();

        CPU robotPlayer = ( CPU ) myApp.getMyBoard().getPlayers().get(1);
        int preMoveScore = robotPlayer.calculateBoardScore(initialBoardState, true);
        Assert.assertNotEquals("Position Evaluation should not equal 0 on new board", 0, preMoveScore);
    }

    @Test
    public void testPositionEvalScoreChange() {
        HashMap<String, String> initialOptions = new HashMap<>();
        initialOptions.put("view", "main");
        initialOptions.put("theme", "day");
        initialOptions.put("opponent", "robot");
        initialOptions.put("difficulty", "hard");
        App myApp = new App();
        myApp.startNewGame(new BoardWindow(myApp.getMyGUI(), initialOptions));
        ArrayList<ArrayList<String>> initialBoardState = myApp.getMyBoard().getBoardState();

        CPU robotPlayer = ( CPU ) myApp.getMyBoard().getPlayers().get(1);
        int preMoveScore = robotPlayer.calculateBoardScore(initialBoardState, true);
        myApp.getMyBoard().notifyAI(robotPlayer);
        myApp.getMyBoard().notifyAI(robotPlayer);
        ArrayList<ArrayList<String>> newBoardState = myApp.getMyBoard().getBoardState();
        int postMoveScore = robotPlayer.calculateBoardScore(newBoardState, true);
        Assert.assertNotEquals("After two moves, position Evaluation should have changed", preMoveScore, postMoveScore);
    }

}