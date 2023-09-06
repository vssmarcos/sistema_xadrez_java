package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private Board board;
    private int turn;
    private Color currentPlayer;
    
    private List<Piece> piecesOnTheBoard= new ArrayList<>();
    private List<Piece> capturedPieces= new ArrayList<>();
    
    public ChessMatch(){
        board = new Board(8,8);
        turn =1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }
    
    public int getTurn(){
        return turn;
    }
    public Color getCurrentPlayer(){
        return currentPlayer;
    }
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getRows()];
        for(int i=0; i<board.getRows();i++){
            for(int j=0; j<board.getColumns();j++){
                mat[i][j]= (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }
    
    public boolean [][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }
    
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source,target);
        Piece capturedPiece = makeMove(source,target);
        nextTurn();
        return (ChessPiece) capturedPiece;
        
    }
    
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        if(capturedPiece!= null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        board.placePiece(p, target);
        return capturedPiece;
    }
    
    
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Nao existe peca na posicao desejada");
        }
        if(currentPlayer!=((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("A peca escolhida nao é sua");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("Nao existe movimentos possiveis para a peca escolhida");
        }
    }
    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("A peca escolhida nao pode mover pra posicao de destino");
        }
    }
    
    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }    
    
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column,row).toPosition());
        piecesOnTheBoard.add(piece);
    }
    
    
    private void initialSetup(){
         
        placeNewPiece('c', 2, new Rook(Color.WHITE,board));
        placeNewPiece('d', 1, new King(Color.WHITE,board));

        placeNewPiece('f', 7, new Rook(Color.BLACK, board));
        placeNewPiece('e', 7, new Rook(Color.BLACK, board));
        placeNewPiece('e', 8, new Rook(Color.BLACK, board));
        placeNewPiece('d', 8, new King(Color.BLACK, board));
    }
    
}
