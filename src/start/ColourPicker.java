package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class ColourPicker{
    private JFrame colourWindow;
    private JColorChooser picker;

    public ColourPicker(ActionListener listener){
        colourWindow = new JFrame("Pick Colour");
        colourWindow.setLayout(new FlowLayout());

        picker = new JColorChooser();

        colourWindow.add(picker);

        JButton confirm = new JButton("Confirm");
        confirm.setActionCommand("SetColour");
        confirm.addActionListener(listener);
        colourWindow.add(confirm);

        colourWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public JFrame getColourWindow() {
        return colourWindow;
    }

    public JColorChooser getPicker() {
        return picker;
    }
}
