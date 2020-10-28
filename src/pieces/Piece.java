package pieces;

import game.Board;

import java.awt.image.BufferedImage;

public abstract class Piece {
    private BufferedImage pic;

    public Piece(String imageLink){
        pic = Board.getImage(imageLink);
    }

    public abstract boolean validateMove(int[] firstPosition, int[] secondPosition);
    public abstract String toString();

    public BufferedImage getPic() {
        return pic;
    }
}
