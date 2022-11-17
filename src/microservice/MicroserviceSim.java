package microservice;

import board_manager.BoardSaver;
import board_manager.BoardSaverFile;
import board_manager.MicroserviceBoardSaver;
import desktop.StartMenu;
import pieces.MicroservicePiece;
import pieces.PieceFactory;

public class MicroserviceSim {

    public static void main(String[] args) {
        BoardSaver boardSaver = new BoardSaverFile();
        MicroserviceBoardSaver boardSaverService = new MicroserviceBoardSaver(boardSaver);

        PieceFactory pieceFactory = new PieceFactory();
        MicroservicePiece pieceService = new MicroservicePiece(pieceFactory);

        StartMenu gui = new StartMenu();
        gui.startGame();
    }

}
