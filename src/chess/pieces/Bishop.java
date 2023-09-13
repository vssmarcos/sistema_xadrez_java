package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

    public Bishop(Color color, Board board) {
        super(color, board);
    }
    
    
    @Override
    public String toString(){
        return "B";
    }
    
    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat =  new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        Position p = new Position(0,0);
        //diagonal a esquerda
        p.setValues(position.getRow()-1, position.getColumn()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow()-1, p.getColumn()-1);
        }
        if(getBoard().positionExists(p)&& isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //diagonal a direita
        p.setValues(position.getRow()-1, position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow()-1, p.getColumn()+1);
        }
        if(getBoard().positionExists(p)&& isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //diagonal pra esquerda
        p.setValues(position.getRow()+1, position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow()+1, p.getColumn()+1);
        }
        if(getBoard().positionExists(p)&& isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
        //diagonal pra esquerda
        p.setValues(position.getRow()+1, position.getColumn()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow()+1, p.getColumn()-1);
        }
        if(getBoard().positionExists(p)&& isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }
}

