package frontEnd;

import javax.swing.*;
import java.awt.*;

public class Settings {
    private JComboBox<String> sizePicker;
    public Color team1 = Color.RED,team2 = Color.BLUE;
    private ButtonGroup teamSelector;
    public void setSizePicker(JComboBox<String> sizePicker){
        this.sizePicker = sizePicker;
    }
    public int getBoardSize(){
        return sizePicker.getSelectedIndex();
    }
    public void setTeamSelector(ButtonGroup buttonGroup){
        this.teamSelector = buttonGroup;
    }
    public boolean getStartingTeam() {
        return (teamSelector.getSelection().getActionCommand().equals("Start1"));
    }
}
