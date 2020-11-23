package start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ColourPicker extends JPanel{
    private final JColorChooser colorChooser;

    public ColourPicker(ActionListener listener){
        this.setLayout(new FlowLayout());
/*
 *    Title: JColorChooser
 *    Author: Oracle
 *    Site owner/sponsor: oracle.com
 *    Date: 2020
 *    Code version: edited edited 20 August 2020
 *    Availability: https://docs.oracle.com/javase/7/docs/api/javax/swing/JColorChooser.html (Accessed 9 November 2020)
 *    Modified:  Converted from C# to Java
 */
//got lucky as I found this by typing color and intelj suggested it.
        colorChooser = new JColorChooser();
        this.add(colorChooser);
//End of non-original code

        JButton confirm = new JButton("Confirm");
        confirm.setActionCommand("SetColour");
        confirm.addActionListener(listener);
        this.add(confirm);
    }

    public Color getColour(){
        return colorChooser.getColor();
    }
}