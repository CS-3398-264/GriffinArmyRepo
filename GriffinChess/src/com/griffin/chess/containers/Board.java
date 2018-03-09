package com.griffin.chess.containers;

import com.griffin.chess.pieces.Piece;
import com.griffin.chess.players.CPU;
import com.griffin.chess.players.Human;
import com.griffin.chess.players.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import static java.lang.Integer.parseInt;

public class Board extends Observable {
    private HashMap<String, String> gameOptions;
    private ArrayList<Player> players;
    private int activePlayer;
    private  ArrayList<ArrayList<String>> boardState;
    private ArrayList<Integer> selectedCell;
    private ArrayList<Integer> targetCell;
    private ArrayList<ArrayList<Integer>> destinations;

    Board(HashMap<String, String> options, Player playerOne) {
        gameOptions = options;
        players = new ArrayList<>();
        players.add(playerOne);
        addSecondPlayer();
        activePlayer = 0;
        boardState = generateBlankBoard();
        selectedCell = new ArrayList<>();
        targetCell = new ArrayList<>();
        destinations = new ArrayList<>();
        addPieces();

        // experimental Piece/AI Observer pattern add-on
        for (Player player : players) {
            for (Piece piece : player.getPieces())
                addObserver(piece);
            addObserver(player);
        }
        // debugging difficulty levels...
        System.out.println("difficulty level: " + options.get("difficulty"));
    }

    private void addSecondPlayer() {
        int id = 1;
        if (gameOptions.get("opponent").equals("human")) {
            players.add(new Human(id));
        } else {
            players.add(new CPU(id, players.get(0)));
        }
    }

    private ArrayList<ArrayList<String>> generateBlankBoard() {
        boardState = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            boardState.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                boardState.get(row).add("-");
            }
        }
        return boardState;
    }

    private void generateNewBoard() {
        boardState = generateBlankBoard();
        addPieces();
        markSpecialCells();
    }

    private void markSpecialCells() {
        // check if something is selected
        if (!selectedCell.isEmpty()) {
            int selRow = selectedCell.get(0);
            int selCol = selectedCell.get(1);
            String cellState = boardState.get(selRow).get(selCol);
            boardState.get(selRow).set(selCol, cellState.concat("~"));
            // check for any highlighted destinations
            if (!destinations.isEmpty()) {
                for (ArrayList<Integer> destination : destinations) {
                    int destRow = destination.get(0);
                    int destCol = destination.get(1);
                    cellState = boardState.get(destRow).get(destCol);
                    if (destRow != selRow || destCol != selCol)
                        if (cellState.length() >= 4)
                            // appropriately mark "enemy target" destinations
                            boardState.get(destRow).set(destCol, cellState.concat("x"));
                        else
                            boardState.get(destRow).set(destCol, cellState.concat("."));
                }
                if (!targetCell.isEmpty()) {
                    int targetRow = targetCell.get(0);
                    int targetCol = targetCell.get(1);
                    cellState = boardState.get(targetRow).get(targetCol);
                    boardState.get(targetRow).set(targetCol, cellState.concat("?"));
                }
            }
        }
    }

    private void addPieces() {
        for (Player player : players) {
            for (Piece piece : player.getPieces()) {
                if (piece.isAlive()) {
                    boardState.get(piece.getRow()).set(piece.getCol(),
                            Integer.toString(piece.getOwner())
                                    + piece.getType()
                                    + setID(piece.getID()));
                }
            }
        }
    }

    private String setID(int pieceID) {
        if (pieceID < 10)
            return "0" + Integer.toString(pieceID);
        return Integer.toString(pieceID);
    }

    public void setSelected(String selected) {
        targetCell.clear();
        int row = parseInt(selected.substring(0,1));
        int col = parseInt(selected.substring(1));
        String cellState = boardState.get(row).get(col);
        // check for an available move/capture
        String moveCheck = cellState.substring(cellState.length()-1);
        switch (moveCheck) {
            case "x":
                targetCell.add(row);
                targetCell.add(col);
                break;
            case ".":
                targetCell.add(row);
                targetCell.add(col);
                break;
            default:
                selectedCell.clear();
                if (!boardState.get(row).get(col).equals("-")) {
                    int owner = Integer.parseInt(String.valueOf(boardState.get(row).get(col).charAt(0)));
                    int pieceID = Integer.parseInt(boardState.get(row).get(col).substring(2, 4));
                    if (owner == activePlayer) {
                        selectedCell.add(row);
                        selectedCell.add(col);
                        setDestinations(pieceID);
                    }
                }
                break;
        }
        updateDisplay();
    }

    private void setDestinations(int pieceID) {
        destinations.clear();
        destinations = players.get(activePlayer).getPieces().get(pieceID).getAvailableMoves();
    }

    public void updateDisplay() {
        generateNewBoard();
        setChanged();
        notifyObservers(boardState);
    }

    public void confirmMove() {
        boolean kingKilled = false;
        int pieceRow = 0;
        int pieceCol = 0;
        if (!selectedCell.isEmpty()) {
            pieceRow = selectedCell.get(0);
            pieceCol = selectedCell.get(1);
        }
        String cellState = boardState.get(pieceRow).get(pieceCol);
        int targetRow;
        int targetCol = 0;
        int pieceID = Integer.parseInt(cellState.substring(2,4));
        int targetID;
        int enemyID;
        int passantRow;
        String targetState = "-";
        String passantState = "";
        Piece activePiece = players.get(activePlayer).getPieces().get(pieceID);


        // handle human move
        if (players.get(activePlayer).getType().equals("human")) {

            // en passant crap
            System.out.printf("confirming move for playerID: %d\n", activePlayer);
            System.out.printf("Piece type: %s\n", players.get(activePlayer).getPieces().get(pieceID).getType());

            // handle captures
            if (!targetCell.isEmpty()) {
                targetRow = targetCell.get(0);
                targetCol = targetCell.get(1);
                // identify the piece
                pieceID = Integer.parseInt(cellState.substring(2, 4));
                // check for piece in target cell
                targetState = boardState.get(targetRow).get(targetCol);
                // kill target piece if it exists
                if (targetState.length() >= 4) {
                    targetID = Integer.parseInt(targetState.substring(2, 4));
                    enemyID = (activePlayer + 1) % players.size();
                    // call the enemy players' com.griffin.chess.pieces' kill method
                    players.get(enemyID).getPieces().get(targetID).kill();
                    if (players.get(enemyID).getPieces().get(targetID).getType().equals("♔") ||
                            players.get(enemyID).getPieces().get(targetID).getType().equals("♚")) {
                        kingKilled = true;
                    }
                }
                int moveDistance = Math.abs(targetCol - activePiece.getCol());

                if (activePiece.getType().equals("♚") && moveDistance == 2) {
                    int castleID;
                    // check for a king that's moving more than 1 space(castling)
                    if (activePlayer == 0) {
                        if (targetCol == 2) {
                            // bottom-side, left castling
                            castleID = Integer.parseInt(boardState.get(pieceRow).get(0).substring(2,4));
                            players.get(activePlayer).getPieces().get(castleID).movePiece(pieceRow, 3);
                        } else {
                            // bottom-side, right castling
                            castleID = Integer.parseInt(boardState.get(pieceRow).get(7).substring(2,4));
                            players.get(activePlayer).getPieces().get(castleID).movePiece(pieceRow, 5);
                        }
                    } else {
                        if (targetCol == 6) {
                            // top-side, left castling
                            castleID = Integer.parseInt(boardState.get(pieceRow).get(7).substring(2,4));
                            players.get(activePlayer).getPieces().get(castleID).movePiece(pieceRow, 5);
                        } else {
                            // top-side, right castling
                            castleID = Integer.parseInt(boardState.get(pieceRow).get(0).substring(2,4));
                            players.get(activePlayer).getPieces().get(castleID).movePiece(pieceRow, 3);
                        }
                    }
                }
                // handle normal piece move here (castling falls-through and hits this too, to move king)
                activePiece.movePiece(targetRow, targetCol);
            }

            // check for en passant
            // determine eligible row for player
            if (activePlayer == 0) passantRow = 3;
            else passantRow = 4;
            if (targetState.length() > 1 && targetState.substring(0,1).equals("-")) {
                passantState = boardState.get(passantRow).get(targetCol);
            }
            if (activePiece.getType().equals("♙") && pieceRow == passantRow && targetState.length() > 1 && targetState.substring(0,1).equals("-")) {
                System.out.printf("active pawn at %d,%d is en passant eligible\n", pieceRow, pieceCol);
                if (targetCol == pieceCol - 1 || targetCol == pieceCol + 1) {
                    System.out.printf("passantState is %s\n", passantState);
                    if (passantState.length() > 1) {
                        System.out.println("passantState length: " + passantState.length());
                        targetID = Integer.parseInt(passantState.substring(2,4));
                        System.out.println("targetID: " + targetID);
                        activePlayer = (activePlayer + 1) % players.size();
                        players.get(activePlayer).getPieces().get(targetID).kill();
                        System.out.printf("num of pieces in target: %d", players.get(activePlayer).getPieces().size());
                    }
                }
            }

        // handle computer (AI) move
        } else if (players.get(activePlayer).getType().equals("robot")) {

            System.out.println("AI TAKING TURN");   // <-- debugging AI
        }

        if (!kingKilled) {
            // toggle the active player
            activePlayer = (activePlayer + 1) % players.size();
            // update the view
            selectedCell.clear();
            destinations.clear();
            targetCell.clear();
            updateDisplay();
            // let the computer (AI) know to go
            if (players.get(activePlayer).getType().equals("robot")) {
                notifyAI(players.get(activePlayer));
                if (!players.get(0).getPieces().get(12).isAlive()) {
                    setChanged();
                    ArrayList<ArrayList<String>> winBoard = generateBlankBoard();
                    Integer winningPlayer = activePlayer;
                    String winString = "Player " + winningPlayer;
                    winBoard.get(0).set(0, winString);
                    notifyObservers(winBoard);
                }
            }
        } else {
            setChanged();
            ArrayList<ArrayList<String>> winBoard = generateBlankBoard();
            Integer winningPlayer = activePlayer + 1;
            String winString = "Player " + winningPlayer;
            winBoard.get(0).set(0, winString);
            notifyObservers(winBoard);
        }
    }

    public void notifyAI(Player aiPlayer) {

        aiPlayer.takeAITurn(gameOptions.get("difficulty"));
        confirmMove();
    }

    public ArrayList<ArrayList<String>> getBoardState() {
        return boardState;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
