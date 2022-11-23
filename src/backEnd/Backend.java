package backEnd;

import Data.Move;
import pieces.*;

public class Backend {
    private Piece[][] board;
    public static final int BOARD_MAX_INDEX = 7,BOARD_MIN_INDEX=0;
    private final BackendClient client;
    private boolean currentTeam;
    public Backend(BackendClient responder){
        this.client = responder;
    }
    public void start(){
        client.startGame();
    }

    public void setBoard(Piece[][] board){
        this.board = board;
    }
    public void setDefaultBoard() {
        board = new Piece[BOARD_MAX_INDEX + 1][BOARD_MAX_INDEX + 1];
        PieceFactory factory = new PieceFactory();
        boolean team = true;
        for (int i = 0; i < 2; i++) {
            int y = team? Backend.BOARD_MIN_INDEX :Backend.BOARD_MAX_INDEX ;
            board[0][y] = factory.makePiece(PieceFactory.PieceNum.Rook,team);
            board[1][y] = factory.makePiece(PieceFactory.PieceNum.Knight,team);
            board[2][y] = factory.makePiece(PieceFactory.PieceNum.Bishop,team);
            board[3][y] = factory.makePiece(PieceFactory.PieceNum.King,team);
            board[4][y] = factory.makePiece(PieceFactory.PieceNum.Queen,team);
            board[5][y] = factory.makePiece(PieceFactory.PieceNum.Bishop,team);
            board[6][y] = factory.makePiece(PieceFactory.PieceNum.Knight,team);
            board[7][y] = factory.makePiece(PieceFactory.PieceNum.Rook,team);

            for (int j = BOARD_MIN_INDEX; j < BOARD_MAX_INDEX + 1; j++) {
                int k = team?Backend.BOARD_MIN_INDEX + 1:Backend.BOARD_MAX_INDEX -1;
                board[j][k] = factory.makePiece(PieceFactory.PieceNum.Pawn,team);
            }
            team = false;
        }
        updateBoardState();
    }
    public void updateBoardState(){
        client.updateScreen(board);
    }
    public void setCurrentTeam(boolean team){
        this.currentTeam = team;
    }
    public void falseMove(Move move){
        Piece piece = getPiece(move);
        if (piece == null){
            return;
        }
        if (piece.validateMove(move,board)){
            fakeMove(move);
        }
    }
    private void fakeMove(Move move){
        Piece[][] tempBoard = copy(board);
        move(move,tempBoard);
        client.updateScreen(tempBoard);
    }
    private Piece[][] copy(Piece[][] board){
        int size = BOARD_MAX_INDEX + 1;
        Piece[][] newBoard = new Piece[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, BOARD_MAX_INDEX);
        }
        return newBoard;
    }
    private void printBoard(){
        for (Piece[] ps: board) {
            for (Piece p: ps) {
                if(p != null)
                    System.out.print("[" + p + "]");
                else
                    System.out.print("[]");
            }
            System.out.print("\n");
        }
    }
    private void move(Move move, Piece[][] board){
        Piece piece = getPiece(move);
        if (piece == null)
            return;
        board[move.fromX][move.fromY] = null;
        board[move.toX][move.toY] = piece;
    }
    public void trueMove(Move move){
        Piece piece = getPiece(move);
        if (checkWin(move.toX, move.toY)){
            client.winGame(currentTeam);
        }
        else if (piece != null && piece.validateMove(move,board)){
            move(move,board);
            client.updateScreen(board);
        }

    }
    public void requestProper(){
        client.updateScreen(board);
    }
    private boolean checkWin(int toX, int toY) {
        Move move = new Move(toX,toY,toX,toY,false,false);
        Piece target = getPiece(move);
        if (target == null)
            return false;
        return target.toString().equals("King");
    }
    public void kill(String name){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] != null && board[i][j].toString().equals(name))
                    board[i][j] = null;
            }

        }
    }
    public void saveBoard(String name){
        client.saveBoard(name,board);
    }
    public Piece getPiece(Move move){
        return board[move.fromX][move.fromY];
    }


}
