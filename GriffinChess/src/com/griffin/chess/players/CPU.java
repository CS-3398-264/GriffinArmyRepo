package com.griffin.chess.players;

import com.griffin.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class CPU extends aPlayer {
    private ArrayList<ArrayList<String>> currentBoardState;
    private Player enemy;

    private final int[][] PAWN_POS = new int[][]
        { {0, 0, 0, 0, 0, 0, 0, 0},
        {1, 2, 2,-2,-2, 2, 2, 1},
        {1,-1,-2, 0, 0,-2,-1, 1},
        {0, 0, 0, 4, 4, 0, 0, 0},
        {1, 1, 2, 5, 5, 2, 1, 1},
        {2, 2, 4, 6, 6, 4, 2, 2},
        {10,10,10,10,10,10,10,10},
        {0, 0, 0, 0, 0, 0, 0, 0} };

    private final int[][] ROOK_POS = new int[][]
        { {0, 0, 0, 1, 1, 0, 0, 0},
        {-1, 0, 0, 0, 0, 0, 0,-1},
        {-1, 0, 0, 0, 0, 0, 0,-1},
        {-1, 0, 0, 0, 0, 0, 0,-1},
        {-1, 0, 0, 0, 0, 0, 0,-1},
        {-1, 0, 0, 0, 0, 0, 0,-1},
        {1, 2, 2, 2, 2, 2, 2, 1},
        {0, 0, 0, 0, 0, 0, 0, 0} };

    private final int[][] QUEEN_POS = new int[][]
        { {-4,-2,-2,-1,-1,-2,-2,-4},
        {-2, 0, 0, 0, 0, 0, 0,-2},
        {-2, 0, 1, 1, 1, 1, 0,-2},
        {-1, 0, 1, 1, 1, 1, 0,-1},
        {0, 0, 1, 1, 1, 1, 0, 0},
        {-2, 0, 1, 1, 1, 1, 0,-2},
        {1, 2, 2, 2, 2, 2, 2, 1},
        {-4,-2,-2,-1,-1,-2,-2,-4} };

    private final int[][] KING_POS = new int[][]
        { {4, 6, 2, 0, 0, 2, 6, 4},
        {4, 4, 0, 0, 0, 0, 4, 4},
        {-2,-4,-4,-4,-4,-4,-4,-2},
        {-4,-6,-6,-8,-8,-6,-6,-4},
        {-6,-8,-8,-10,-10,-8,-8,-6},
        {-6,-8,-8,-10,-10,-8,-8,-6},
        {-6,-8,-8,-10,-10,-8,-8,-6},
        {-6,-8,-8,-10,-10,-8,-8,-6} };

    private final int[][] KNIGHT_POS = new int[][]
        { {-10,-8,-6,-6,-6,-6,-8,-10},
        {-8,-4, 0, 0, 0, 0, -4, -8},
        {-6, 0, 2, 3, 3, 2, 0, -6},
        {-6, 1, 3, 2, 2, 3,  1, -6},
        {-6, 0, 3, 2, 2, 3, 0, -6},
        {-6, 1, 2, 3, 3, 2, 1, -6},
        {-8, -4, 0, 1, 1, 0, -4, -8},
        {-10,-8,-6,-6,-6,-6,-8,-10} };

    private final int[][] BISHOP_POS = new int[][]
        { {-4,-2,-2,-2,-2,-2,-2,-4},
        {-2, 1, 0, 0, 0, 0, 1, -2},
        {-2, 0, 2, 2, 2, 2, 2, -2},
        {-2, 0, 2, 2, 2, 2,  0, -2},
        {-2, 1, 1, 2, 2, 1, 1, -2},
        {-2, 0, 1, 2, 2, 1, 0, -2},
        {-2, 0, 0, 0, 0, 0, 0, -2},
        {-4,-2,-2,-2,-2,-2,-2,-4} };

    public CPU(int ID, Player opponent) {
        super(ID);
        enemy = opponent;
    }

    public String getType() { return "robot"; }

    public void takeAITurn(String difficulty) {
        switch (difficulty) {
            case "easy":
                takeRandomMove();
                break;
            case "hard":
                takeSmarterMove("hard");
                break;
            default:
                takeSmarterMove("normal");
                break;
        }
    }

    private void takeRandomMove() {
        int pieceMax = getPiecesWithMoves().size();
        Piece randomPiece = getPiecesWithMoves().get(new Random().nextInt(pieceMax));
        int movesMax =  randomPiece.getAvailableMoves().size();
        ArrayList<Integer> randomMove = randomPiece.getAvailableMoves().get(new Random().nextInt(movesMax));
        int targetRow = randomMove.get(0);
        int targetCol = randomMove.get(1);

        // check for castling here
        checkForCastling(randomPiece, targetCol);

        // check for piece in target cell
        String targetState = currentBoardState.get(targetRow).get(targetCol);
        // kill target piece if it exists
        if (targetState.length() >= 4) {
            int targetID = Integer.parseInt(targetState.substring(2, 4));
            // call the enemy players' pieces' kill method
            enemy.getPieces().get(targetID).kill();
        }
        randomPiece.movePiece(targetRow, targetCol);
        System.out.println("'easy' move made!");
    }

    private void checkForCastling(Piece movingPiece, int targetCol) {
        int pieceRow = movingPiece.getRow();
        int moveDistance = Math.abs(targetCol - movingPiece.getCol());

        if (movingPiece.getType().equals("♚") && moveDistance == 2) {
            int castleID;
            if (targetCol == 2) {
                // top-side, right castling
                castleID = Integer.parseInt(currentBoardState.get(pieceRow).get(0).substring(2,4));
                getPieces().get(castleID).movePiece(pieceRow, 3);
            } else {
                // top-side, left castling
                castleID = Integer.parseInt(currentBoardState.get(pieceRow).get(7).substring(2,4));
                getPieces().get(castleID).movePiece(pieceRow, 5);
            }
        }
    }

    private ArrayList<ArrayList<String>> getBoardCopy() {
        ArrayList<ArrayList<String>> boardCopy = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            boardCopy.add(new ArrayList<>());
            for (int col = 0; col < 8; col++) {
                boardCopy.get(row).add(currentBoardState.get(row).get(col));
            }
        }
        return boardCopy;
    }

    private void takeSmarterMove(String difficulty) {
        Piece bestPiece = null;
        Piece targetPiece;
        ArrayList<Integer> bestMove;
        boolean evalPosition = false;
        int bestValue = -9999;
        int bestRow = 0;
        int bestCol = 0;

        for (Piece piece : getPiecesWithMoves()) {
            ArrayList<ArrayList<Integer>> movesList = piece.getAvailableMoves();
            int actualRow = piece.getRow();
            int actualCol = piece.getCol();
            for (ArrayList<Integer> move : movesList) {
                int moveRow = move.get(0);
                int moveCol = move.get(1);
                // build "future board" for each potential move
                ArrayList<ArrayList<String>> boardCopy = getBoardCopy();
                String cellState = currentBoardState.get(actualRow).get(actualCol);
                boardCopy.get(actualRow).set(actualCol, "-");
                boardCopy.get(moveRow).set(moveCol, cellState);
                // compute score for that board
                if (difficulty.equals("hard")) evalPosition = true;
                if (calculateBoardScore(boardCopy, evalPosition) > bestValue) {
                    bestValue = calculateBoardScore(boardCopy, evalPosition);
                    bestPiece = piece;
                    bestMove = move;
                    bestRow = bestMove.get(0);
                    bestCol = bestMove.get(1);
                }
            }
        }
        // check for piece in target cell
            String targetState = currentBoardState.get(bestRow).get(bestCol);
        // kill target piece if it exists
        if (targetState.length() >= 4) {
            int targetID = Integer.parseInt(targetState.substring(2, 4));
            // call the enemy players' pieces' kill method
            targetPiece = enemy.getPieces().get(targetID);
            targetPiece.kill();
            if (bestPiece != null) {
                System.out.printf("%s captured %s at %d,%d\n", bestPiece.getType(), targetPiece.getType(), bestRow, bestCol);
            }
        }
        if (bestPiece != null) {
            // check for castling here
            checkForCastling(bestPiece, bestCol);
            bestPiece.movePiece(bestRow, bestCol);
            System.out.printf("AI moved %s%d to %d,%d\n", bestPiece.getType(), bestPiece.getID(), bestRow, bestCol);
        }
        System.out.printf("%s move taken.\n", difficulty);
    }

    private int calculateBoardScore(ArrayList<ArrayList<String>> board, boolean evalPosition) {
        int score = 0;
        for (int row = 0;row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (!board.get(row).get(col).equals("-")) {
                    int cellPlayerID = Integer.parseInt(board.get(row).get(col).substring(0,1));
                    String cellPiece = board.get(row).get(col).substring(1,2);
                    if (cellPlayerID == playerID) {
                        score += getPieceValue(cellPiece);
                        if (evalPosition) score += getPositionValue(cellPiece, row, col);
                    } else {
                        score -= getPieceValue(cellPiece);
                        if (evalPosition) score -= getPositionValue(cellPiece, row, col);
                    }
                }
            }
        }
        return score;
    }

    private int getPieceValue(String piece) {
        switch (piece) {
            case "♙":
                return 10;
            case "♖":
            case "♜":
                return 50;
            case "♘":
                return 30;
            case "♗":
                return 30;
            case "♕":
                return 90;
            case "♔" :
            case "♚" :
                return 888;
            default:
                return 0;
        }
    }

    private int getPositionValue(String piece, int row, int col) {
        switch (piece) {
            case "♙":
                return PAWN_POS[row][col];
            case "♖":
            case "♜":
                return ROOK_POS[row][col];
            case "♘":
                return KNIGHT_POS[row][col];
            case "♗":
                return BISHOP_POS[row][col];
            case "♕":
                return QUEEN_POS[row][col];
            case "♔" :
            case "♚" :
                return KING_POS[row][col];
            default:
                return 0;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.currentBoardState = ( ArrayList<ArrayList<String>> ) arg;
    }
}
