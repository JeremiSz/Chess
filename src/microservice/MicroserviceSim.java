package microservice;

import board_manager.BoardSaver;
import board_manager.BoardSaverFile;
import board_manager.MicroserviceBoardSaver;

public class MicroserviceSim {
    public static void main(String[] args) {
        BoardSaver boardSaver = new BoardSaverFile();
        MicroserviceBoardSaver boardSaverService = new MicroserviceBoardSaver(boardSaver);
    }

}
