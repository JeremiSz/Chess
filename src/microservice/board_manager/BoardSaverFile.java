package microservice.board_manager;
import java.io.*;

public class BoardSaverFile implements BoardSaver{

    @Override
    public void save(String boardName, Object board) throws IOException{
        FileOutputStream outputStream = new FileOutputStream(boardName + ".brd");
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(board);
        out.close();

    }

    @Override
    public Object load(String boardName) throws IOException, ClassNotFoundException {
        File file = new File(boardName);
        FileInputStream inputStream = new FileInputStream(file);
        ObjectInputStream out = new ObjectInputStream(inputStream);
        return out.readObject();
    }
}
