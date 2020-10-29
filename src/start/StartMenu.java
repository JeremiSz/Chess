package start;

import javax.swing.*;
import java.awt.*;

public class StartMenu {
    private JFrame startWindow;
    private JButton team1Colour, team2Colour;
    private JComboBox sizePicker;

    private ColourPicker colourPicker;
    private StartListener listener;

    public StartMenu(Color team1, Color team2){
        listener = new StartListener();
        colourPicker = new ColourPicker(listener);

        startWindow = new JFrame("Start Menu");
        startWindow.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new FlowLayout());
        startWindow.add(panel);
        startWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel sizeLabel = new JLabel("Size");
        panel.add(sizeLabel);

        String[] sizes = {"Small","Medium","Large"};
        sizePicker = new JComboBox(sizes);
        panel.add(sizePicker);

        JLabel team1Label = new JLabel("Team 1");
        panel.add(team1Label);

        team1Colour = new JButton("■");
        team1Colour.setForeground(team1);
        team1Colour.setActionCommand("SetTeam1");
        team1Colour.addActionListener(listener);
        panel.add(team1Colour);

        JLabel team2Label = new JLabel("Team 2");
        panel.add(team2Label);

        team2Colour = new JButton("■");
        team2Colour.setForeground(team2);
        team2Colour.setActionCommand("SetTeam2");
        team2Colour.addActionListener(listener);
        panel.add(team2Colour);

        JButton startGame = new JButton("Start");
        startGame.setActionCommand("Start");
        startGame.addActionListener(listener);
        panel.add(startGame);

        startWindow.pack();
    }

    public JFrame getStartWindow() {
        return startWindow;
    }

    public void updateColour(Color colour, boolean team){
        if(team) {
            team1Colour.setForeground(colour);
            Start.team1 = colour;
        }
        else {
            team2Colour.setForeground(colour);
            Start.team2 = colour;
        }
        startWindow.repaint();
    }

    public int getSize(){
        return sizePicker.getSelectedIndex();
    }

    public ColourPicker getColourPicker() {
        return colourPicker;
    }

    public void cleanUp(){
        team2Colour = null;
        team1Colour = null;
        sizePicker = null;

        colourPicker.cleanUp();
        colourPicker = null;

        listener = null;

        startWindow.dispose();

        Start.startMenu = null;
    }
}
