package frontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ColourPicker extends JPanel{
    private final JColorChooser colorChooser;

    public ColourPicker(ActionListener listener){
        this.setLayout(new FlowLayout());
        colorChooser = new JColorChooser();
        this.add(colorChooser);

        JButton confirm = new JButton("Confirm");
        confirm.setActionCommand("SetColour");
        confirm.addActionListener(listener);
        this.add(confirm);
    }

    public Color getColour(){
        return colorChooser.getColor();
    }
}