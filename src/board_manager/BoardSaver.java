package board_manager;

import java.io.IOException;

public interface BoardSaver {
    void save(String boardName, Object board) throws IOException;
    Object load(String boardName) throws IOException, ClassNotFoundException;
}
