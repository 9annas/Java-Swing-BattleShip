import controller.Controller;
import model.audio.Music;
import view.MainMenu;

import javax.swing.*;
import java.awt.*;

public class Principal
{
    public static void main(String[] args)
    {
        UIDefaults uiDefaults = UIManager.getLookAndFeelDefaults();
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.darkGray));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));

        JFrame.setDefaultLookAndFeelDecorated(true);

        Music.playSound();
        new Controller(new MainMenu());
    }
}