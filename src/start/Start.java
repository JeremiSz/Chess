package start;

import start.ColourPicker;
import start.StartListener;

import javax.swing.*;
import java.awt.*;

public class Start {
    static Color team1;
    static Color team2;
    static JFrame startMenu;
    static ColourPicker colourPicker;
    static StartListener listener;

    public static void main(String[] args) {
        team1 = Color.BLACK;
        team2 = Color.WHITE;


        startMenu = makeStart();
        listener = new StartListener();
        colourPicker = new ColourPicker(listener);
        startMenu.setVisible(true);

    }

    private static JFrame makeStart(){
        JFrame output = new JFrame("Start Menu");
        JPanel panel = new JPanel(new FlowLayout());
        output.add(panel);
        output.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel sizeLabel = new JLabel("Size");
        panel.add(sizeLabel);

        String[] sizes = {"Small","Medium","Large"};
        JComboBox sizePicker = new JComboBox(sizes);
        panel.add(sizePicker);

        JLabel team1Label = new JLabel("Team 1");
        panel.add(team1Label);

        JButton team1Colour = new JButton("■");
        team1Colour.setForeground(team1);
        team1Colour.setActionCommand("SetTeam1");
        panel.add(team1Colour);

        JLabel team2Label = new JLabel("Team 2");
        panel.add(team2Label);

        JButton team2Colour = new JButton("■");
        team2Colour.setForeground(team2);
        team2Colour.setActionCommand("SetTeam2");
        panel.add(team2Colour);

        output.pack();

        return output;
    }
}
