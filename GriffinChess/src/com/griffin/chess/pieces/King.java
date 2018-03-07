package com.griffin.chess.pieces;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class King extends aPiece {
    King(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        canCastle = true;
    }

    public String getType() {
        return "♔";
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
        int leftDir;
        int rightDir;
        int leftCastleCol;
        int rightCastleCol;
        if (getOwner() == 1) {
            castleRow = 0;
            leftCastleCol = 7;
            rightCastleCol = 0;
            leftDir = 1;
            rightDir = -1;
        }
        else {
            castleRow = 7;
            leftCastleCol = 0;
            rightCastleCol = 7;
            leftDir = -1;
            rightDir = 1;
        }
        ArrayList<Integer> leftCastlePos = new ArrayList<>();
        ArrayList<Integer> rightCastlePos = new ArrayList<>();
        leftCastlePos.add(castleRow);
        rightCastlePos.add(castleRow);
        leftCastlePos.add(leftCastleCol);
        rightCastlePos.add(rightCastleCol);

        if (canCastle()) {
            String leftCastleID = getCellState(leftCastlePos);//.substring(2,4);
            String rightCastleID = getCellState(rightCastlePos);//.substring(2,4);
            System.out.printf("left castle %s\n", leftCastleID);
            System.out.printf("right castle %s\n", rightCastleID);

            // if () <-- perform check on left castle (in player? board?)
            // if () <-- perform check on right castle
            if (leftCastleID.length() > 1)
                if (leftCastleID.substring(0,2).equals(getOwner() + "♖")) {
                    /*
                    boolean allowCastle = true;
                    for (int col = getCol(); col != leftCastleCol; col += leftDir) {
                        ArrayList<Integer> cellPos = new ArrayList<>();
                        cellPos.add(castleRow);
                        cellPos.add(col);
                        if (!getCellState(cellPos).substring(0).equals("-")) {
                            allowCastle = false;
                            break;
                        }
                    }
                    if (allowCastle) castlingMoves.add(goLeft(2));
                */
                    //System.out.print("left castle in place.. king checking castle move");
                    checkSingleMove(castlingMoves, goLeft(2));
                }
            if (rightCastleID.length() > 1)
                if (rightCastleID.substring(0,2).equals(getOwner() + "♖")) {
                    /*
                    boolean allowCastle = true;
                    for (int col = getCol(); col != leftCastleCol; col += leftDir) {
                        ArrayList<Integer> cellPos = new ArrayList<>();
                        cellPos.add(castleRow);
                        cellPos.add(col);
                        if (!getCellState(cellPos).substring(0).equals("-")) {
                            allowCastle = false;
                            break;
                        }
                    }
                    if (allowCastle) castlingMoves.add(goLeft(2));
                */
                    //System.out.print("left castle in place.. king checking castle move");
                    checkSingleMove(castlingMoves, goRight(2));
                }

        }

        return castlingMoves;
    }
}
