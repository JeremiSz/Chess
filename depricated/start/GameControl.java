package start;

import javax.swing.*;

public class GameControl {
    private static JFrame window;

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame(){
        if(window == null) {
            window = new JFrame("Chess");
            window.setResizable(false);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        new StartMenu(window);
    }
}