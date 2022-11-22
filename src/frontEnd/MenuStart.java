package frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuStart extends Menu implements ActionListener {
    private final JFrame window;
    private final UIStart menu;
    private final Settings settings;
    private String boardName;
    private final ColourPicker picker;
    private boolean targetTeam = false;

    public MenuStart(JFrame window,FrontEndClient client,Settings settings){
        super(client);
        this.window = window;
        this.settings = settings;
        this.menu = new UIStart(this,window,settings);
        this.boardName = "";
        this.picker = new ColourPicker(this);

        this.client = client;
    }

    @Override
    public void clean() {
        window.setVisible(false);
        window.remove(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case("SetTeam1"):
                setTeam(true);
                break;
            case ("SetTeam2"):
                setTeam(false);
                break;
            case("SetColour"):
                setColour();
                break;
            case("Start"):
                StartGame();
                break;
            case("SetBoard"):
                setBoard();
                break;
            default:
                System.err.println("Unset Button pressed");
                break;
        }
    }
    private void setTeam(boolean team){
        window.setVisible(false);
        this.targetTeam = team;

        window.remove(menu);
        window.add(picker);
        window.pack();

        window.setVisible(true);
    }
    private void setColour(){
        window.setVisible(false);
        Color colour = picker.getColour();
        menu.updateColour(colour,targetTeam);
        if (targetTeam)
            settings.team1 = colour;
        else
            settings.team2 = colour;
        window.remove(picker);
        window.add(menu);
        window.pack();

        window.setVisible(true);
    }
    private void setBoard(){
        boardName = JOptionPane.showInputDialog("Name of board");
    }
    private void StartGame(){
        client.setStartingTeam(settings.getStartingTeam());
        client.setBoard(boardName);
    }
    private class UIStart extends JPanel{
        private final JButton[] teamsColours = new JButton[2];
        public UIStart(ActionListener listener,JFrame window,Settings settings){
            setUpWindow(window);
            createSizePicker(settings);
            createTeams(listener,settings);
            createButton("Choose Board","SetBoard",listener);
            createButton("Start","Start",listener);
            finishWindow(window);
        }
        public void updateColour(Color colour, boolean team){

            if(team)
                teamsColours[0].setForeground(colour);
            else
                teamsColours[1].setForeground(colour);

            window.repaint();
        }
        private void setUpWindow(JFrame window){
            window.setVisible(false);
            window.add(this);
            this.setPreferredSize(new Dimension(150,170));
            this.setLayout(new FlowLayout());

        }
        private void createSizePicker(Settings settings){
            createLabel("Size");
            String[] sizes = {"Small","Medium","Large"};
            JComboBox<String> sizePicker = new JComboBox<>(sizes);
            settings.setSizePicker(sizePicker);
            this.add(sizePicker);
        }
        private void createTeams(ActionListener listener,Settings settings){
            ButtonGroup teamSelector = new ButtonGroup();
            createTeam("Team 1",Color.RED,"SetTeam1","Start1",listener,teamSelector,0);
            createTeam("Team 2",Color.BLUE,"SetTeam2","Start2",listener,teamSelector,1);
            teamSelector.setSelected(teamSelector.getElements().nextElement().getModel(),true);
            settings.setTeamSelector(teamSelector);
        }
        private void createButton(String text,String action,ActionListener listener){
            JButton chooseBoard = new JButton(text);
            chooseBoard.setActionCommand(action);
            chooseBoard.addActionListener(listener);
            this.add(chooseBoard);
        }
        private void finishWindow(JFrame window){
            window.pack();
            window.setVisible(true);
        }
        private void createTeam(String label, Color defaultColour,String colourAction,String startAction,ActionListener listener,ButtonGroup group,int button){
            createLabel(label);
            teamsColours[button] = new JButton("■");
            teamsColours[button].setForeground(defaultColour);
            teamsColours[button].setActionCommand(colourAction);
            teamsColours[button].addActionListener(listener);
            this.add(teamsColours[button]);


            JRadioButton team1Start = new JRadioButton();
            team1Start.setActionCommand(startAction);
            group.add(team1Start);
            this.add(team1Start);
        }
        private void createLabel(String text){
            this.add(new JLabel(text));
        }

    }
}
