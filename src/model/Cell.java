package model;

import controller.GameController;

import java.io.Serializable;
/**
 * This class describe the slot for Chess in Chessboard
 * */
public class Cell implements Serializable {
    // the position for chess
    private ChessPiece piece;

    public Cell() {
    }

    public Cell(ChessPiece piece) {
        this.piece = piece;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = GameController.nullcell.getPiece();
    }

    public void swapPiece(Cell cell){
        ChessPiece piece1=this.piece;
        setPiece(cell.getPiece());
        cell.setPiece(piece1);
    }

}
