package dataAccess;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataAccess {
    void save(Board board) throws IOException;
    Object load(String name) throws FileNotFoundException;
}
