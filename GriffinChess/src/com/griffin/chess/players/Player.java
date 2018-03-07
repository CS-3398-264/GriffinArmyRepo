package com.griffin.chess.players;

import com.griffin.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Observer;

public interface Player extends Observer {
    String getType();
    boolean getCastlePerformed();
    ArrayList<Piece> getPieces();
    ArrayList<Piece> getPiecesWithMoves();
    ArrayList<Piece> generateNewPieces();
    void takeAITurn(String difficulty);
}
