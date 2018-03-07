package com.griffin.chess.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class aPiece implements Piece {
    private int owner;
    private int ID;
    protected int row;
    protected int col;
    protected boolean canCastle;
    private boolean isAlive;
    private ArrayList<ArrayList<String>> boardState;

    aPiece(int ownerID, int pieceID, int startRow, int startCol) {
        owner = ownerID;
        ID = pieceID;
        row = startRow;
        col = startCol;
        isAlive = true;
        canCastle = false;
    }

    public abstract String getType();

    public int getID() {
        return ID;
    }

    public int getOwner() {
        return owner;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    int getHomeRow() {
        if (owner == 0) return 6;
        else return 1;
    }

    public void movePiece(int newRow, int newCol) {
        canCastle = false;
        row = newRow;
        col = newCol;
    }

    ArrayList<Integer> goForward(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 0, -1);
        else
            return inBoundsMove(distance, 0, 1);
    }
    ArrayList<Integer> goBackward(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 0, 1);
        else
            return inBoundsMove(distance, 0, -1);
    }
    ArrayList<Integer> goLeft(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -1, 0);
        else
            return inBoundsMove(distance, 1, 0);
    }
    ArrayList<Integer> goRight(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 1, 0);
        else
            return inBoundsMove(distance, -1, 0);
    }
    ArrayList<Integer> goFrontLeft(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -1, -1);
        else
            return inBoundsMove(distance, 1, 1);
    }
    ArrayList<Integer> goFrontRight(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 1, -1);
        else
            return inBoundsMove(distance, -1, 1);
    }
    ArrayList<Integer> goBackLeft(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, -1, 1);
        else
            return inBoundsMove(distance, 1, -1);
    }
    ArrayList<Integer> goBackRight(int distance) {
        if (owner == 0)
            return inBoundsMove(distance, 1, 1);
        else
            return inBoundsMove(distance, -1, -1);
    }
    
    //Movement for the Knight
    ArrayList<Integer> goKnightDownRight() {
        if (owner == 0)
            return inBoundsMove(1, 1, 2);
        else
            return inBoundsMove(1, -1, -2);
    }
    ArrayList<Integer> goKnightDownLeft() {
        if (owner == 0)
            return inBoundsMove(1, -1, 2);
        else
            return inBoundsMove(1, 1, -2);
    }
    ArrayList<Integer> goKnightUpRight() {
        if (owner == 0)
            return inBoundsMove(1, 1, -2);
        else
            return inBoundsMove(1, -1, 2);
    }
    ArrayList<Integer> goKnightUpLeft() {
        if (owner == 0)
            return inBoundsMove(1, -1, -2);
        else
            return inBoundsMove(1, 1, 2);
    }
    ArrayList<Integer> goKnightRightDown() {
        if (owner == 0)
            return inBoundsMove(1, 2, 1);
        else
            return inBoundsMove(1, -2, -1);
    }
    ArrayList<Integer> goKnightLeftDown() {
        if (owner == 0)
            return inBoundsMove(1, -2, 1);
        else
            return inBoundsMove(1, 2, -1);
    }
    ArrayList<Integer> goKnightRightUp() {
        if (owner == 0)
            return inBoundsMove(1, 2, -1);
        else
            return inBoundsMove(1, -2, 1);
    }
    ArrayList<Integer> goKnightLeftUp() {
        if (owner == 0)
            return inBoundsMove(1, -2, -1);
        else
            return inBoundsMove(1, 2, 1);
    }

    private ArrayList<Integer> inBoundsMove(int distance, int dirX, int dirY) {
        ArrayList<Integer> newMove = new ArrayList<>();

        int newRow = row + (dirY * distance);
        int newCol = col + (dirX * distance);
        if (boundsCheck(newRow) && boundsCheck(newCol)) {
            newMove.add(newRow);
            newMove.add(newCol);
        }
        else {
            newMove.add(row);
            newMove.add(col);
        }
        return newMove;
    }

    private boolean boundsCheck(int move) {
        return move <= 7 && move >= 0;
    }

    public abstract ArrayList<ArrayList<Integer>> getAvailableMoves();

    public boolean isAlive() {
        return isAlive;
    }

    public boolean canCastle() { return canCastle; }

    public void kill() {
        isAlive = false;
    }

    // **new** methods for collision/capturing
    boolean pieceFound(ArrayList<Integer> newMove) {
        String cell = getCellState(newMove);
        return isOccupied(cell);
    }

    void checkSingleMove(ArrayList<ArrayList<Integer>> movesList, ArrayList<Integer> newMove) {
        movesList.add(newMove);
        String cell = getCellState(newMove);
        if (isOccupied(cell) && isOwnPiece(cell))
            movesList.remove(movesList.size() - 1);
    }

    String getCellState(List<Integer> position) {
        return boardState.get(position.get(0)).get(position.get(1));
    }

    boolean isOccupied(String cellState) {
        return !cellState.substring(0, 1).equals("-");
    }

    boolean isOwnPiece(String cellState) {
        return cellState.length() >= 4 && Integer.parseInt(cellState.substring(0, 1)) == getOwner();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.boardState = ( ArrayList<ArrayList<String>> ) arg;
    }
}
