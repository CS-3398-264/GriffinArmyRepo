package com.griffin.chess.pieces;

import java.util.ArrayList;

public class Queen extends aPiece {
    Queen(int ownerID, int pieceID, int startRow, int startCol) {
        super(ownerID, pieceID, startRow, startCol);
    }

    public String getType() {
        return "♕";
    }

    public ArrayList<ArrayList<Integer>> getAvailableMoves() {
        ArrayList<ArrayList<Integer>> availableMoves = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            checkSingleMove(availableMoves, goFrontRight(i));
            if (pieceFound(goFrontRight(i))) break;
        }
        for (int i = 1; i <= 7; i++) {
            checkSingleMove(availableMoves, goFrontLeft(i));
            if (pieceFound(goFrontLeft(i))) break;
        }
        for (int i = 1; i <= 7; i++) {
            checkSingleMove(availableMoves, goBackRight(i));
            if (pieceFound(goBackRight(i))) break;
         }
        for (int i = 1; i <= 7; i++) {
            checkSingleMove(availableMoves, goBackLeft(i));
            if (pieceFound(goBackLeft(i))) break;
        }
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
