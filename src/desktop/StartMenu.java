package desktop;

import start.ColourPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel{
    private JFrame window;
    public JButton team1Colour, team2Colour;
    private JComboBox<String> sizePicker;
    private ButtonGroup teamSelector;
    private final ColourPicker picker;

    public StartMenu(ActionListener listener){
        createWindow();
        createMenu(listener);
        picker = new ColourPicker(listener);
    }

    private void createWindow(){
        window = new JFrame("Chess");
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(150,170));
        this.setLayout(new FlowLayout());
    }
    private void createMenu(ActionListener listener){
        createSizeChooser();
        createTeamColour("Team 1", this.team1Colour, Color.RED, "SetTeam1",listener);
        createTeamStart("Start1",true);
        createTeamColour("Team 2", this.team2Colour, Color.BLUE, "SetTeam2",listener);
        createTeamStart("Start2",false);
        createButton("Choose Board", "SetBoard",listener);
        createButton("Start", "Start",listener);
        finishWindow();
    }
    private void finishWindow(){
        window.pack();
        window.setVisible(true);
    }
    private void createTeamStart(String action, boolean team){
        teamSelector = new ButtonGroup();
        JRadioButton team1Start = new JRadioButton();
        team1Start.setActionCommand(action);
        teamSelector.add(team1Start);
        this.add(team1Start);
        if (team)
            teamSelector.setSelected(team1Start.getModel(),true);
    }

    private void createButton(String Choose_Board, String SetBoard,ActionListener listener) {
        JButton chooseBoard = new JButton(Choose_Board);
        chooseBoard.setActionCommand(SetBoard);
        chooseBoard.addActionListener(listener);
        this.add(chooseBoard);
    }

    private void createTeamColour(String text, JButton teamColour, Color red, String SetTeam1,ActionListener listener) {
        this.add(new JLabel(text));
        teamColour = new JButton("■");
        teamColour.setForeground(red);
        teamColour.setActionCommand(SetTeam1);
        teamColour.addActionListener(listener);
        this.add(teamColour);
    }

    private void createSizeChooser() {
        JLabel sizeLabel = new JLabel("Size");
        this.add(sizeLabel);
        String[] sizes = {"Small","Medium","Large"};
        sizePicker = new JComboBox<>(sizes);
        this.add(sizePicker);
    }
    public void disappear(){
        window.setVisible(false);
    }
    public void appear(){
        window.setVisible(true);
    }
    public void setColourPicker(){
        changeMenu(this,picker);
    }
    public void setMenu(){
        changeMenu(picker,this);
    }
    private void changeMenu(JPanel from, JPanel to){
        window.setVisible(false);
        window.remove(from);
        window.add(to);
        window.pack();
        window.setVisible(true);
    }
    public void updateColour(boolean team){
        Color colour = picker.getColour();
        if(team)
            team1Colour.setForeground(colour);
        else
            team2Colour.setForeground(colour);

        window.repaint();
    }
    public boolean getTeamStart(){
        return teamSelector.getSelection().getActionCommand().equals("Start1");
    }
    public int getBoardSize(){
        return sizePicker.getSelectedIndex();
    }
    public close(){
        window.
    }

}
