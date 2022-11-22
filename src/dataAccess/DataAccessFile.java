package dataAccess;

import Data.Board;

import java.io.*;

public class DataAccessFile implements DataAccess{
    private final String EXTENSION = ".brd";
    @Override
    public void save(Board board) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(board.name + EXTENSION);
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(board.state);
        out.close();
    }

    @Override
    public Object load(String name) throws FileNotFoundException {
        try {
            File file = new File(name + EXTENSION);
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream out = new ObjectInputStream(inputStream);
            return out.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            throw new FileNotFoundException("Board could not be found or is corrupted");
        }
    }
}
