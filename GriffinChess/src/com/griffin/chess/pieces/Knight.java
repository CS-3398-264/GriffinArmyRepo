package com.griffin.chess.pieces;

import java.util.ArrayList;

public class Knight extends aPiece {
    Knight(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
    }

    public String getType() {
        return "♘";
    }

    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();
        checkSingleMove(availableMoves, goKnightUpRight());
        checkSingleMove(availableMoves, goKnightUpLeft());
        checkSingleMove(availableMoves, goKnightDownRight());
        checkSingleMove(availableMoves, goKnightDownLeft());

        checkSingleMove(availableMoves, goKnightRightUp());
        checkSingleMove(availableMoves, goKnightLeftUp());
        checkSingleMove(availableMoves, goKnightRightDown());
        checkSingleMove(availableMoves, goKnightLeftDown());
        return availableMoves;
    }
}
