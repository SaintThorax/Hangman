package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tfpva on 28-5-2017.
 */
public class gamePlay extends JPanel {
    public int difficulty;
    public String secretWord;
    public JLabel guessLb, mistakesLb;
    public JTextField guessTf;
    private JPanel contentPane, drawPane, playPane, guessPane, mistakePane, charsPane;

    public gamePlay(){
        setPanels();
        setLabels();

        add(contentPane);
    }

    public void setLabels(){
        guessLb = new JLabel("Guess");
        mistakesLb = new JLabel("Mistakes : ");
    }

    public void setPanels(){
        contentPane = new JPanel(new BorderLayout(4,0)); //holds all other parent panes

        drawPane = new JPanel(); //Holds the drawing field
        drawPane.setPreferredSize(new Dimension(330,340));
        drawPane.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));

        playPane = new JPanel(new BorderLayout(0,20)); //Holds guess, mistake en charsPane.
        playPane.setPreferredSize(new Dimension(430, 300));
        playPane.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));


        guessPane = new JPanel(); //holds guess label and textfield;
        mistakePane = new JPanel(); //holds mistakes label and mistakes chars
        charsPane = new JPanel(); //Holds all chars in the secret word.

        contentPane.add(drawPane, BorderLayout.WEST);
        contentPane.add(playPane, BorderLayout.EAST);
    }

    public void getDifficulty(int setDifficulty){
        difficulty = setDifficulty;
    }

    public void getSecretWord(String setSecretWord){
        secretWord = setSecretWord;
    }
}
