package com.griffin.chess.pieces;

import java.util.ArrayList;

public class Rook extends aPiece {
    public Rook(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
        canCastle = true;
    }

    public String getType() {
        if (canCastle) return "♜";
        else return "♖";
    }

    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            checkSingleMove(availableMoves, goForward(i));
            if (pieceFound(goForward(i))) break;
        }
        for (int i = 1; i <= 7; i++) {
            checkSingleMove(availableMoves, goBackward(i));
            if (pieceFound(goBackward(i))) break;
        }
        for (int i = 1; i <= 7; i++) {
            checkSingleMove(availableMoves, goRight(i));
            if (pieceFound(goRight(i))) break;
        }
        for (int i = 1; i <= 7; i++) {
            checkSingleMove(availableMoves, goLeft(i));
            if (pieceFound(goLeft(i))) break;
        }
        return availableMoves;
    }
}
