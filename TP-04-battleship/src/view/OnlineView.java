package view;

import controller.IController;
import model.network.Client;
import model.network.Host;
import util.EmptyJPanel;
import util.GameColor;
import util.GameFont;
import util.MenuJButton;

import javax.swing.*;
import java.awt.*;

public class OnlineView
{
    private final IController controller;
    private final MainMenu mainMenu;
    private Host host;

    private final ImageIcon background = new ImageIcon("src\\resources\\OnlineMenu.jpg");

    private final JFrame frame = new JFrame();

    private final JPanel buttonPanel = new JPanel();
    private final JPanel hostWaitingPanel = new JPanel();
    private final JPanel joinPanel = new JPanel();
    private final JPanel joinTextPanel = new JPanel();

    private final JLabel backgroundLabel = new JLabel(background);
    private final JLabel hostWaitingLabel = new JLabel("Waiting...", SwingConstants.CENTER);
    private final JLabel ipLabel = new JLabel("IP Address", SwingConstants.CENTER);
    private final JLabel wrongIPLabel = new JLabel("", SwingConstants.CENTER);

    private final JTextField ipTextField = new JTextField("127.0.0.1", 8);

    private final MenuJButton hostButton = new MenuJButton("Host");
    private final MenuJButton joinButton = new MenuJButton("Join");
    private final MenuJButton backButton = new MenuJButton("Back");
    private final MenuJButton joinBackButton = new MenuJButton("Back");
    private final MenuJButton cancelButton = new MenuJButton("Cancel");
    private final MenuJButton connectButton = new MenuJButton("Connect");

    public OnlineView(IController controller, MainMenu mainMenu)
    {
        this.controller = controller;
        this.mainMenu = mainMenu;
        initFrame();
        setLayouts();
        addElementsToFrame();
        elementsSettings();
        initListener();
        frame.setVisible(true);
    }

    private void initFrame()
    {
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void setLayouts()
    {
        backgroundLabel.setLayout(new GridLayout(2,3));
        buttonPanel.setLayout(new GridLayout(0,1,0,10));
        hostWaitingPanel.setLayout(new GridLayout(0,1,0,10));
        joinPanel.setLayout(new GridLayout(0,1, 0, 10));
        joinTextPanel.setLayout(new GridLayout(0,1));
    }

    private void addElementsToFrame()
    {
        frame.add(backgroundLabel, BorderLayout.CENTER);

        fillWithEmptyPanel();
        backgroundLabel.add(buttonPanel);

        buttonPanel.add(hostButton);
        buttonPanel.add(joinButton);
        buttonPanel.add(backButton);

        joinPanel.add(ipLabel);
        joinPanel.add(ipTextField);
        joinPanel.add(wrongIPLabel);
        joinPanel.add(connectButton);
        joinPanel.add(joinBackButton);

        hostWaitingPanel.add(hostWaitingLabel);
        hostWaitingPanel.add(new EmptyJPanel());
        hostWaitingPanel.add(cancelButton);
    }

    private void fillWithEmptyPanel()
    {
        for (int i = 0; i < 5 ; i++)
        {
            backgroundLabel.add(new EmptyJPanel());
        }
    }

    private void elementsSettings()
    {
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,70,10,10));

        joinPanel.setOpaque(false);
        joinPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        joinTextPanel.setOpaque(false);

        hostWaitingPanel.setOpaque(false);
        hostWaitingPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        ipLabel.setFont(GameFont.MAIN_MENU);
        ipLabel.setForeground(GameColor.MenuTextColor);

        hostWaitingLabel.setFont(GameFont.MAIN_MENU);
        hostWaitingLabel.setForeground(GameColor.MenuTextColor);

        wrongIPLabel.setFont(GameFont.MAIN_MENU);
        wrongIPLabel.setForeground(Color.red);

        ipTextField.setBackground(new Color(0,0,0,0));
        ipTextField.setOpaque(false);
        ipTextField.setFont(GameFont.MAIN_MENU);
        ipTextField.setHorizontalAlignment(JTextField.CENTER);
    }

    private void initListener()
    {
        hostButton.addActionListener(e -> host());
        joinButton.addActionListener(e -> join());
        backButton.addActionListener(e -> back());
        joinBackButton.addActionListener(e -> joinBack());
        cancelButton.addActionListener(e-> cancel());
        connectButton.addActionListener(e -> connect());
    }

    private void host()
    {
        backgroundLabel.remove(buttonPanel);
        backgroundLabel.add(hostWaitingPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.repaint();

        host = new Host(controller, this);
    }

    private void join()
    {
        backgroundLabel.remove(buttonPanel);
        backgroundLabel.add(joinPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.repaint();
    }

    private void back()
    {
        frame.dispose();
        mainMenu.visible();
    }

    private void joinBack()
    {
        backgroundLabel.remove(joinPanel);
        backgroundLabel.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.repaint();
    }

    private void cancel()
    {
        host.cancel();
        backgroundLabel.remove(hostWaitingPanel);
        backgroundLabel.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.repaint();
    }

    private void connect()
    {
        new Client(ipTextField.getText(), controller, this);
    }

    public void dispose()
    {
        frame.dispose();
    }

    public void setTextWrongIPLabel(String text)
    {
        wrongIPLabel.setText(text);
    }
}