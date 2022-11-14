package start;

import javax.swing.*;
import java.awt.*;

public class StartMenu  extends  JPanel{
    private final JButton team1Colour,team2Colour;
    private final JComboBox<String> sizePicker;
    private final ButtonGroup teamSelector;

    private final JFrame window;

    public StartMenu(JFrame window){
        StartListener listener = new StartListener(window,this);
        new ColourPicker(listener);

        this.window = window;
        this.window.add(this);

        this.setPreferredSize(new Dimension(150,170));

        this.setLayout(new FlowLayout());

        JLabel sizeLabel = new JLabel("Size");
        this.add(sizeLabel);

        String[] sizes = {"Small","Medium","Large"};
        sizePicker = new JComboBox<>(sizes);
        this.add(sizePicker);

        JLabel team1Label = new JLabel("Team 1");
        this.add(team1Label);

        team1Colour = new JButton("■");
        team1Colour.setForeground(Color.RED);
        team1Colour.setActionCommand("SetTeam1");
        team1Colour.addActionListener(listener);
        this.add(team1Colour);

        teamSelector = new ButtonGroup();
        /*
         *    Title: JRadioButton Class
         *    Author: Oracle
         *    Site owner/sponsor: https://docs.oracle.com/
         *    Date: 2020
         *    Code version: edited Jun 24 14:46:37
         *    Availability: https://docs.oracle.com/javase/7/docs/api/javax/swing/JRadioButton.html (Accessed 26 November 2020)
         *    Modified:  used constructor and the idea of adding them to ButtonGroup
         *****************************************************/
        JRadioButton team1Start = new JRadioButton();
        team1Start.setActionCommand("Start1");
        teamSelector.add(team1Start);
        this.add(team1Start);
//end of un-original code

        JLabel team2Label = new JLabel("Team 2");
        this.add(team2Label);

        team2Colour = new JButton("■");
        team2Colour.setForeground(Color.BLUE);
        team2Colour.setActionCommand("SetTeam2");
        team2Colour.addActionListener(listener);
        this.add(team2Colour);

         /*    Title: JRadioButton Class
         *    Author: Oracle
         *    Site owner/sponsor: https://docs.oracle.com/
         *    Date: 2020
         *    Code version: edited Jun 24 14:46:37
         *    Availability: https://docs.oracle.com/javase/7/docs/api/javax/swing/JRadioButton.html (Accessed 26 November 2020)
         *    Modified:  used constructor and the idea of adding them to ButtonGroup
         *****************************************************/
        JRadioButton team2Start = new JRadioButton();
        team2Start.setActionCommand("Start2");
        teamSelector.add(team2Start);
        this.add(team2Start);
        teamSelector.setSelected(team1Start.getModel(),true);
//End of non-original code

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

    public boolean getTeamStart(){
        return (teamSelector.getSelection().getActionCommand().equals("Start1"));
    }
}