package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class gameMenu extends JPanel {
    private static JButton gameStart, gameExit;
    private JLabel gameTitle, gameCredits;
    private JPanel framePanel, titlePanel, buttonPanel;
    public static int selectMode;

    public gameMenu(){
        setButtons();
        setLabels();
        createMenu();

        selectMode = 0;
    }

    public void createMenu(){
        framePanel = new JPanel(new BorderLayout(0,50));
        titlePanel = new JPanel();
        buttonPanel = new JPanel(new BorderLayout());

        titlePanel.add(gameTitle);
        buttonPanel.add(gameStart, BorderLayout.LINE_START);
        buttonPanel.add(gameExit, BorderLayout.LINE_END);

        framePanel.add(titlePanel, BorderLayout.PAGE_START);
        framePanel.add(buttonPanel, BorderLayout.CENTER);

        add(framePanel);
    }

    public static void menuSelect(){
        System.out.println("menuselect start");
        gameStart.setText("Singleplayer");
        gameExit.setText("Multiplayer");
        gameExit.removeActionListener(new gameExitListener());
        gameExit.addActionListener(new gameOptionsStartListener());

        selectMode = 1;
    }

    public void setButtons(){
        gameStart = new JButton("Start game");
        gameStart.setName("Single");
        gameStart.setFont(new Font("Century Gothic", Font.BOLD, 20));
        gameStart.setFocusPainted(false);
        gameStart.setBackground(Color.decode("#ECF0F1"));
        gameStart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        gameStart.setPreferredSize(new Dimension(200,60));
        gameStart.addActionListener(new gameOptionsStartListener());

        gameExit = new JButton("Exit game");
        gameExit.setName("Multi");
        gameExit.setFont(new Font("Century Gothic", Font.BOLD, 20));
        gameExit.setFocusPainted(false);
        gameExit.setBackground(Color.decode("#ECF0F1"));
        gameExit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        gameExit.setPreferredSize(new Dimension(200,60));
        gameExit.addActionListener(new gameExitListener());
    }

    public void setLabels(){
        gameTitle = new JLabel("Hangman");
        gameTitle.setFont(new Font("Century Gothic", Font.BOLD, 100));
    }
}

class gameExitListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameMenu.selectMode == 1){
            new gameOptionsStartListener();
        } else {
            System.exit(0);
        }
    }
}

class gameOptionsStartListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){

        if (gameMenu.selectMode == 1){
            JButton btnName = (JButton)e.getSource();
            if (btnName.getName().equals("Single")){
                Main.startSinglePlayer();
            } else {
                Main.startGameOptions();
            }
        }

        gameMenu.menuSelect();

        //Main.startGameOptions();
    }
}