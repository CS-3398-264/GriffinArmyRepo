package com.griffin.chess.pieces;

import java.util.ArrayList;

public class King extends aPiece {
    King(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        canCastle = true;
    }

    public String getType() {
        if (canCastle) return "♚";
        else return "♔";
    }

    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();
        checkSingleMove(availableMoves, goFrontRight(1));
        checkSingleMove(availableMoves, goFrontLeft(1));
        checkSingleMove(availableMoves, goBackRight(1));
        checkSingleMove(availableMoves, goBackLeft(1));
        checkSingleMove(availableMoves, goForward(1));
        checkSingleMove(availableMoves, goBackward(1));
        checkSingleMove(availableMoves, goRight(1));
        checkSingleMove(availableMoves, goLeft(1));
        availableMoves.addAll(addCastlingMoves());
        return availableMoves;
    }

    private ArrayList<ArrayList<Integer>> addCastlingMoves() {
        ArrayList<ArrayList<Integer>> castlingMoves = new ArrayList<>();
        int castleRow;
        int leftCastleCol;
        int rightCastleCol;
        if (getOwner() == 1) {
            castleRow = 0;
            leftCastleCol = 7;
            rightCastleCol = 0;
        }
        else {
            castleRow = 7;
            leftCastleCol = 0;
            rightCastleCol = 7;
        }
        ArrayList<Integer> leftCastlePos = new ArrayList<>();
        ArrayList<Integer> rightCastlePos = new ArrayList<>();
        leftCastlePos.add(castleRow);
        rightCastlePos.add(castleRow);
        leftCastlePos.add(leftCastleCol);
        rightCastlePos.add(rightCastleCol);

        if (canCastle()) {
            String leftCastleID = getCellState(leftCastlePos);
            String rightCastleID = getCellState(rightCastlePos);

            if (leftCastleID.length() > 1)
                if (leftCastleID.substring(0,2).equals(getOwner() + "♜")) {
                    checkSingleMove(castlingMoves, goLeft(2));
                }
            if (rightCastleID.length() > 1)
                if (rightCastleID.substring(0,2).equals(getOwner() + "♜")) {
                    checkSingleMove(castlingMoves, goRight(2));
                }

        }
        return castlingMoves;
    }
}
