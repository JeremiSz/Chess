package board_manager;
import java.io.*;

public class BoardSaverFile implements BoardSaver{
    private final String EXTENSION = ".brd";

    @Override
    public void save(String boardName, Object board) throws IOException{
        FileOutputStream outputStream = new FileOutputStream(boardName + EXTENSION);
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(board);
        out.close();
    }
    @Override
    public Object load(String boardName) throws IOException, ClassNotFoundException {
        File file = new File(boardName + EXTENSION);
        FileInputStream inputStream = new FileInputStream(file);
        ObjectInputStream out = new ObjectInputStream(inputStream);
        return out.readObject();
    }
}
