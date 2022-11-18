package backEnd;

import game.Board;
import pieces.*;

public class Backend {
    private Piece[][] board;
    public static final int BOARD_MAX_INDEX = 7,BOARD_MIN_INDEX=0;
    private final BackendResponder responder;
    private boolean currentTeam;
    public Backend(BackendResponder responder){
        this.responder = responder;
    }

    public void setBoard(Piece[][] board){
        this.board = board;
    }
    public void setDefaultBoard() {
        board = new Piece[BOARD_MAX_INDEX + 1][BOARD_MAX_INDEX + 1];
        PieceFactory factory = new PieceFactory();
        boolean team = true;
        for (int i = 0; i < 2; i++) {
            factory.makePiece(PieceFactory.PieceNum.Rook,0,team,board);
            factory.makePiece(PieceFactory.PieceNum.Knight,1,team,board);
            factory.makePiece(PieceFactory.PieceNum.Bishop,2,team,board);
            factory.makePiece(PieceFactory.PieceNum.King,3,team,board);
            factory.makePiece(PieceFactory.PieceNum.Queen,4,team,board);
            factory.makePiece(PieceFactory.PieceNum.Bishop,5,team,board);
            factory.makePiece(PieceFactory.PieceNum.Knight,6,team,board);
            factory.makePiece(PieceFactory.PieceNum.Rook,7,team,board);

            for (int j = BOARD_MIN_INDEX; j < BOARD_MAX_INDEX + 1; j++) {
                factory.makePiece(PieceFactory.PieceNum.Pawn,j,team,board);
            }
            team = false;
        }
    }
    public void updateBoardState(){
        responder.updateScreen(board);
    }
    public void setCurrentTeam(boolean team){
        this.currentTeam = team;
    }
    public void falseMove(Move move){
        Piece piece = board[move.fromX][move.fromY];
        if (piece.validateMove(move,board)){
            fakeMove(move);
        }
    }
    private void fakeMove(Move move){
        Piece[][] tempBoard = board.clone();
        move(move,tempBoard);
        responder.updateScreen(tempBoard);
    }
    private void move(Move move, Piece[][] board){
        Piece piece = board[move.fromX][move.fromX];
        board[move.fromX][move.fromY] = null;
        board[move.toX][move.toY] = piece;
    }
    public void trueMove(Move move){
        Piece piece = board[move.fromX][move.fromY];
        if (piece.validateMove(move,board)){
            move(move,board);
            responder.updateScreen(board);
        }
        if (checkWin(move.toX, move.toY)){
            responder.winGame();
        }

    }
    public void requestProper(){
        responder.updateScreen(board);
    }
    private boolean checkWin(int toX, int toY) {
        Piece target = board[toX][toY];
        return target != null && target.toString().equals("King");
    }


}
