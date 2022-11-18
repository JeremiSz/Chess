package dataAccess;

public class Board {
    public Board(String name, Object board){
        this.name = name;
        this.state = board;
    }
    public String name;
    public Object state;
}
