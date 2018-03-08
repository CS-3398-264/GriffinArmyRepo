package com.griffin.chess.pieces;

import java.util.ArrayList;

public class Pawn extends aPiece {
    Pawn(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
    }

    public String getType() {
        return "♙";
    }

    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        String cell;
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();

        if (row == getHomeRow()) {
            for (int i = 1; i <= 2; i++) {
                checkSingleMove(availableMoves, goForward(i));
                if (pieceFound(goForward(i))) {
                    if (!availableMoves.isEmpty())
                        availableMoves.remove(availableMoves.size()-1);
                    break;
                }
            }
        } else if (!pieceFound(goForward(1))) {
            availableMoves.add(goForward(1));
        }

        // check left diag
        cell = getCellState(goFrontLeft(1));
        if (isOccupied(cell) && !isOwnPiece(cell))
            availableMoves.add(goFrontLeft(1));

        // check right diag
        cell = getCellState(goFrontRight(1));
        if (isOccupied(cell) && !isOwnPiece(cell))
            availableMoves.add(goFrontRight(1));

        // check for en passant
        int eligibleRow;
        // determine eligible row for player
        if (getOwner() == 0) eligibleRow = 3;
        else eligibleRow = 4;
        // check target row
        if (getRow() == eligibleRow) {
            cell = getCellState(goLeft(1));
            if (isOccupied(cell) && !isOwnPiece(cell))
                // display target square (diagonal)
                availableMoves.add(goFrontLeft(1));
            // if taken, kill other pawn <-- this can't be done here...
        }
        return availableMoves;
    }
}
