package pieces;

import game.Board;

import java.io.Serializable;

/**
 * A abstract class which defines a Piece. It is the general class used by all pieces in the game of chess.
 * @author Jeremi Szlapka
 */
public abstract class Piece implements Serializable {
    private final String name,symbol;
    private final boolean team;

    /**
     * Piece 3 argument constructor sets all values as it should only be called by the constructor of
     * the concrete classes which define the specific piece types
     * @param symbol the symbol character which is displayed on the board
     * @param name the name of the piece type
     * @param team the team of the piece
     */
    public Piece(String symbol,String name,boolean team){
        this.name = name;
        this.team = team;
        this.symbol = symbol;
    }

    /**
     * abstract method called when moving a piece. It defines when a move is valid or not.
     * @param firstPosition this is the position the piece moves from
     * @param secondPosition this is the position the piece moves to
     * @return if the move is valid it returns true. IF the move should not be able to be done, it returns false
     */
    public abstract boolean validateMove(int[] firstPosition, int[] secondPosition);

    /**
     * method to get the name of the piece
     * @return name of the piece
     */
    public String toString(){
        return name;
    }

    /**
     * method returns the team of the piece
     * @return true if it is owned by the upper team and false if owned by the lower team
     */
    public boolean getTeam() {
        return team;
    }

    /**
     * method returns the symbol of the piece
     * @return the symbol of the piece
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * method moves the piece on the board. This covers removing killed pieces
     * and clearing the position the piece comes from. This version takes 4
     * integers to define the 2 positions
     * @param toX the x coordinate of the position the piece was moved from
     * @param toY the y coordinate of the position the piece was moved from
     * @param fromX the x coordinate of the position the piece was moved to
     * @param fromY the y coordinate of the position the piece was moved to
     */
    public void movePiece(int toX, int toY, int fromX, int fromY){
        Board.grid[fromX][fromY] = null;
        Board.grid[toX][toY] = this;
    }

    /**
     * method moves the piece on the board. This covers removing killed pieces
     * and clearing the position the piece comes from. This version takes 2
     * integer arrays of length 2.
     * @param to integer array of length 2 that defines the end position of the piece.
     *           the first position defines the x coordinate and the second position to the y coordinate
     * @param from integer array of length 2 that defines the start position of the piece.
     *             the first position defines the x coordinate and the second position to the y coordinate
     */
    public void movePiece(int[] to, int[] from){
        Board.grid[from[0]][from[1]] = null;
        Board.grid[to[0]][to[1]] = this;
    }

    /**
     * method that checks if the position given can be the final position of a move
     * it checks if the final position either has no piece on it or a piece of the opposite team.
     * @param last integer array of length 2.
     *             The first position is the x coordinate and
     *             the second is the y coordinate of the final position of the move.
     * @return true if the final position is not valid.
     */
    protected boolean isBlocked(int[] last){
        Piece target = Board.grid[last[0]][last[1]];
        return (target != null && target.getTeam() == this.team);
    }
}