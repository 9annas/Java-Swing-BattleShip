package util;

import javax.swing.*;
import java.awt.*;

public class ViewBoardJButton extends JButton
{
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(GameColor.EMPTY_COLOR);

    public ViewBoardJButton(boolean enable)
    {
        setBackground(DEFAULT_BACKGROUND_COLOR);
        setEnabled(enable);
    }
}