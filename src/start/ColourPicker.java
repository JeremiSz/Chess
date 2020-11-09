package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ColourPicker{
    private JFrame colourWindow;
    private JColorChooser picker;

    public ColourPicker(ActionListener listener){
        colourWindow = new JFrame("Pick Colour");
        colourWindow.setLayout(new FlowLayout());
/*****************************************************
 *    Title: JColorChooser
 *    Author: Oracle
 *    Site owner/sponsor: oracle.com
 *    Date: 2020
 *    Code version: edited edited 20 August 2020
 *    Availability: https://docs.oracle.com/javase/7/docs/api/javax/swing/JColorChooser.html (Accessed 9 November 2020)
 *    Modified:  Converted from C# to Java
 *****************************************************/
//got lucky as I found this by typing color and intelj suggested it.
        picker = new JColorChooser();
        colourWindow.add(picker);
//End of non-original code

        JButton confirm = new JButton("Confirm");
        confirm.setActionCommand("SetColour");
        confirm.addActionListener(listener);
        colourWindow.add(confirm);
        colourWindow.pack();

        colourWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JFrame getColourWindow() {
        return colourWindow;
    }
    public JColorChooser getPicker() { return picker; }

    public void cleanUp(){
        picker = null;
        colourWindow.dispose();
    }
}
