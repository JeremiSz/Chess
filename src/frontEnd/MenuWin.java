package frontEnd;

import javax.swing.*;

public class MenuWin extends Menu{
    public MenuWin(boolean winingTeam,FrontEndClient client){
        super(client);
        String output = "Team" + (winingTeam?1:2) + " team won\n\nPlay again?";
        int result = JOptionPane.showConfirmDialog(null, output, "Winner!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            client.restart();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void clean() {}

    @Override
    public void dirtyBit() {}
}
