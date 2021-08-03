package util;

import javax.swing.*;
import java.awt.*;

public class CustomIconJButton extends JButton
{
    public CustomIconJButton(String filename)
    {
        setIcon(filename);
    }

    private void setIcon(String filename)
    {
        setBackground(new Color(0xFFFFFF));
        setPreferredSize(new Dimension(75,75));
        setIcon(new ImageIcon(filename));
    }
}