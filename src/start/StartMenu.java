package start;

import javax.swing.*;
import java.awt.*;

public class StartMenu  extends  JPanel{
    private final JButton team1Colour,team2Colour;
    private final JComboBox sizePicker;

    private final JFrame window;

    public StartMenu(JFrame window){
        StartListener listener = new StartListener(window,this);
        new ColourPicker(listener);

        this.window = window;
        this.window.add(this);

        this.setLayout(new FlowLayout());

        JLabel sizeLabel = new JLabel("Size");
        this.add(sizeLabel);

        String[] sizes = {"Small","Medium","Large"};
        sizePicker = new JComboBox(sizes);
        this.add(sizePicker);

        JLabel team1Label = new JLabel("Team 1");
        this.add(team1Label);

        team1Colour = new JButton("■");
        team1Colour.setForeground(Color.RED);
        team1Colour.setActionCommand("SetTeam1");
        team1Colour.addActionListener(listener);
        this.add(team1Colour);

        JLabel team2Label = new JLabel("Team 2");
        this.add(team2Label);

        team2Colour = new JButton("■");
        team2Colour.setForeground(Color.BLUE);
        team2Colour.setActionCommand("SetTeam2");
        team2Colour.addActionListener(listener);
        this.add(team2Colour);

        JButton chooseBoard = new JButton("Choose Board");
        chooseBoard.setActionCommand("SetBoard");
        chooseBoard.addActionListener(listener);
        this.add(chooseBoard);

        JButton startGame = new JButton("Start");
        startGame.setActionCommand("Start");
        startGame.addActionListener(listener);
        this.add(startGame);

        window.pack();
        window.setVisible(true);
    }

    public void updateColour(Color colour, boolean team){

        if(team)
            team1Colour.setForeground(colour);
        else
            team2Colour.setForeground(colour);

        window.repaint();
    }

    public int getBoardSize(){
         return sizePicker.getSelectedIndex();
    }

    public Color getTeamColour(boolean team){
        if(team)
            return team1Colour.getForeground();
        else
            return team2Colour.getForeground();
    }
}