package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class gameOptions extends JPanel {
    private JLabel headLb, enterWordLb, selectDifLb;
    public static JTextField enterWordTf;
    public static JButton diffEasyBt, diffMedBt, diffHardBt, gameStartBt;
    private JPanel contentPane, optionsPane, wordPane, diffPane, diffButtonPn;
    public static String secretWord;

    public gameOptions(){
        setLabels();
        setTextField();
        setButtons();

        contentPane = new JPanel(new BorderLayout(0,20)); //main pane, holds headlb, options and gamestartbt
        optionsPane = new JPanel(new BorderLayout(0,20)); //holds wordpane and diffpane

        //wordpane <- optionspane <- contentpane
        wordPane = new JPanel(new BorderLayout(0,10));
        wordPane.add(enterWordLb, BorderLayout.PAGE_START);
        wordPane.add(enterWordTf, BorderLayout.CENTER);

        //diffPane <- optionsPane <- contentPane
        diffPane = new JPanel(new BorderLayout(0,10));
        diffPane.add(selectDifLb, BorderLayout.PAGE_START);
        diffPane.add(diffButtonPn, BorderLayout.CENTER);

        optionsPane.add(wordPane, BorderLayout.PAGE_START);
        optionsPane.add(diffPane, BorderLayout.CENTER);

        contentPane.add(headLb, BorderLayout.PAGE_START);
        contentPane.add(optionsPane, BorderLayout.CENTER);
        contentPane.add(gameStartBt, BorderLayout.PAGE_END);

        add(contentPane);
    }

    public void setLabels(){
        JPanel test = new JPanel();
        headLb = new JLabel("Options");
        headLb.setFont(new Font("Century Gothic", Font.BOLD, 30));

        enterWordLb = new JLabel("Enter the secret word");
        selectDifLb = new JLabel("Select the difficulty");

        ArrayList<JLabel> labels = new ArrayList<>();
        labels.add(headLb);
        labels.add(enterWordLb);
        labels.add(selectDifLb);

        for (JLabel label : labels){
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        }
        headLb.setFont(new Font("Century Gothic", Font.BOLD, 32));
    }

    public void setTextField(){
        enterWordTf = new JTextField(20);
        enterWordTf.setText("Testing");
        enterWordTf.setName("secretWordTf");
        enterWordTf.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        enterWordTf.setPreferredSize(new Dimension(80, 30));
        enterWordTf.setFont(new Font("Century Gothic", Font.PLAIN, 24));
        enterWordTf.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setButtons(){
        diffButtonPn = new JPanel(new GridLayout(1,3,10,0));

        diffEasyBt = new JButton("Easy");
        diffEasyBt.setName("EasyBt");

        diffMedBt = new JButton("Medium");
        diffMedBt.setName("MedBt");

        diffHardBt = new JButton("Hard");
        diffHardBt.setName("HardBt");

        gameStartBt = new JButton("Start");
        gameStartBt.setName("StartBt");

        diffButtonPn.add(diffEasyBt);
        diffButtonPn.add(diffMedBt);
        diffButtonPn.add(diffHardBt);

        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(diffEasyBt);
        buttons.add(diffMedBt);
        buttons.add(diffHardBt);
        buttons.add(gameStartBt);

        for(JButton button : buttons){
            button.setFont(new Font("Century Gothic", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.setBackground(Color.decode("#e0e0e0"));
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
            button.setPreferredSize(new Dimension(200,60));
            if (!(button.getName().equals("StartBt"))){
                button.addActionListener(new DifficultyListener());
            }
        }

        gameStartBt.addActionListener(new GameStartListener());
    }

    public static void resetColors(){
        diffEasyBt.setBackground(Color.decode("#e0e0e0"));
        diffMedBt.setBackground(Color.decode("#e0e0e0"));
        diffHardBt.setBackground(Color.decode("#e0e0e0"));
    }

}

class DifficultyListener implements ActionListener {
    public void actionPerformed(ActionEvent e){

        JButton pressedBt = (JButton)e.getSource();
        boolean validInput;
        String name = pressedBt.getName();

        if (name.equals("EasyBt")){
            gameOptions.resetColors();
            pressedBt.setBackground(Color.decode("#86b0f4"));
            Main.setDifficulty(10);
            validInput = true;
        } else if (name.equals("MedBt")){
            gameOptions.resetColors();
            pressedBt.setBackground(Color.decode("#86b0f4"));
            Main.setDifficulty(8);
            validInput = true;
        } else if (name.equals("HardBt")){
            gameOptions.resetColors();
            pressedBt.setBackground(Color.decode("#86b0f4"));
            Main.setDifficulty(6);
            validInput = true;
        } else {
            validInput = false;
        }

    }
}

class GameStartListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        String secretWord = gameOptions.enterWordTf.getText();
        secretWord = secretWord.toUpperCase();

        Main.setSecretWord(secretWord);
        Main.isInputValid();
    }
}