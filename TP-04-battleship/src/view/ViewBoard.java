package view;

import controller.IController;
import util.*;

import javax.swing.*;
import java.awt.*;

public class ViewBoard implements IViewBoard
{
    IController controller;

    JButton[][] defenseBoard = new JButton[10][10];
    JButton[][] attackBoard = new JButton[10][10];

    JFrame frame = new JFrame();

    JPanel centerPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel defensePanel = new JPanel();
    JPanel attackPanel = new JPanel();
    JPanel leftNorthPanel = new JPanel();
    JPanel rightNorthPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel eastPanel = new JPanel();
    JPanel restartPanel = new JPanel();
    JPanel optionPanel = new JPanel();
    JPanel quitPanel = new JPanel();

    CustomIconJButton restartButton = new CustomIconJButton("src/resources/restart.png");
    CustomIconJButton optionButton = new CustomIconJButton("src/resources/setting.png");
    CustomIconJButton quitButton = new CustomIconJButton("src/resources/exit.png");

    ViewBoardJLabel defenseLabel = new ViewBoardJLabel("YOUR BOARD");
    ViewBoardJLabel attackLabel = new ViewBoardJLabel("ATTACK BOARD");
    ViewBoardJLabel consoleOutputLabel = new ViewBoardJLabel("Welcome and good luck");

    public ViewBoard()
    {
        initFrame();
        setLayouts();
        addElementsToFrame();
        initBoards();
        panelSize();
        panelSettings();
        initSidePanelButtonsListener();
        frame.setVisible(true);
    }
    
    @Override
    public void setController(IController controller)
    {
        this.controller = controller;
    }

    private JButton getButtonAt(Position position, JButton[][] board)
    {
        return board[position.getY()][position.getX()];
    }

    @Override
    public void changeTileOfAttackBoard(Position position, int color)
    {
        getButtonAt(position, attackBoard).setBackground(new Color(color));
    }

    @Override
    public void changeTileOfDefenseBoard(Position position, int color)
    {
        getButtonAt(position, defenseBoard).setBackground(new Color(color));
    }

    @Override
    public void disableButtonAt(Position position)
    {
        attackBoard[position.getY()][position.getX()].setEnabled(false);
    }

    @Override
    public void changeConsoleOutput(String output)
    {
        consoleOutputLabel.setText(output);
    }

    //region constructor methods
    private void initFrame()
    {
        frame.setSize(1100, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void setLayouts()
    {
        centerPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new GridLayout(1, 2));

        southPanel.setLayout(new GridLayout(0,1));

        leftPanel.setLayout(new BorderLayout());
        defensePanel.setLayout(new GridLayout(11, 11));
        leftNorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        rightPanel.setLayout(new BorderLayout());
        attackPanel.setLayout(new GridLayout(11, 11));
        rightNorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.setLayout(new GridLayout(0,1));
        restartPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        quitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void addElementsToFrame()
    {
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.WEST);
        frame.add(eastPanel, BorderLayout.EAST);

        centerPanel.add(mainPanel, BorderLayout.CENTER);
        centerPanel.add(southPanel, BorderLayout.SOUTH);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        leftPanel.add(leftNorthPanel, BorderLayout.NORTH);
        leftPanel.add(defensePanel, BorderLayout.CENTER);
        leftPanel.add(new EmptyJPanel(), BorderLayout.EAST);

        leftNorthPanel.add(defenseLabel);

        rightPanel.add(rightNorthPanel, BorderLayout.NORTH);
        rightPanel.add(attackPanel, BorderLayout.CENTER);
        rightPanel.add(new EmptyJPanel(), BorderLayout.WEST);

        rightNorthPanel.add(attackLabel);

        southPanel.add(consoleOutputLabel);

        buttonPanel.add(new EmptyJPanel());
        buttonPanel.add(restartPanel);
        buttonPanel.add(new EmptyJPanel());
        buttonPanel.add(optionPanel);
        buttonPanel.add(new EmptyJPanel());
        buttonPanel.add(quitPanel);

        restartPanel.add(restartButton);
        optionPanel.add(optionButton);
        quitPanel.add(quitButton);
    }

    private void panelSize()
    {
        eastPanel.setPreferredSize(new Dimension(15, eastPanel.getPreferredSize().height));
        leftNorthPanel.setPreferredSize(new Dimension(southPanel.getPreferredSize().width, 40));
        rightNorthPanel.setPreferredSize(new Dimension(southPanel.getPreferredSize().width, 40));
        southPanel.setPreferredSize(new Dimension(southPanel.getPreferredSize().width, 60));
        buttonPanel.setPreferredSize(new Dimension(100, buttonPanel.getPreferredSize().height));
    }

    private void panelSettings() // TODO Name could be more explicit / divide it into multiple sub-method
    {
        centerPanel.setBackground(GameColor.viewBoardBackGround);
        eastPanel.setBackground(GameColor.viewBoardBackGround);
        southPanel.setBackground(GameColor.viewBoardBackGround);
        buttonPanel.setBackground(GameColor.viewBoardBorder);

        mainPanel.setOpaque(false);
        leftPanel.setOpaque(false);
        leftNorthPanel.setOpaque(false);
        rightPanel.setOpaque(false);
        rightNorthPanel.setOpaque(false);
        attackPanel.setOpaque(false);
        defensePanel.setOpaque(false);
        restartPanel.setOpaque(false);
        optionPanel.setOpaque(false);
        quitPanel.setOpaque(false);

        buttonPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        consoleOutputLabel.setFont(GameFont.CONSOLE_OUTPUT);
    }

    private void initBoards()
    {
        initBoard(defenseBoard, defensePanel, false);
        initBoard(attackBoard, attackPanel, true);
    }

    private void initBoard(JButton[][] board, JPanel panel, boolean enable)
    {
        for (int i = 0; i < 11; i++)
        {
            ViewBoardJLabel label = i == 0 ? new ViewBoardJLabel() : new ViewBoardJLabel(Integer.toString(i));
            panel.add(label);
        }

        for (int y = 0; y < 10; y++)
        {
            panel.add(new ViewBoardJLabel(Character.toString((char) ('A' + y)))); //Convert y to A-Z format
            for (int x = 0; x < 10; x++)
            {
                var button = new ViewBoardJButton(enable);
                panel.add(button);
                if (enable)
                    initBoardButtonListener(button, new Position(x, y));
                board[y][x] = button;
            }
        }
    }

    private void initBoardButtonListener(JButton button, Position position)
    {
        button.addActionListener(e -> controller.shoot(position));
    }

    private void initSidePanelButtonsListener()
    {
        quitButton.addActionListener(e -> quit());
        optionButton.addActionListener(e -> option());
        restartButton.addActionListener(e -> restart());
    }

    //endregion
    private void quit()
    {
        controller.quit();
        this.frame.dispose();
    }

    private void option()
    {
        controller.option();
    }

    private void restart()
    {
        controller.restart();
        this.frame.dispose();
    }
}