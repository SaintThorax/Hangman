package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class Main extends JFrame {
    public static JFrame frame;
    public static int difficulty;
    public static String secretWord;

    public static void main(String[] args) {
        createFrame();
        startGameOptions();
    }

    public static void createFrame(){
        frame = new JFrame();
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("Hangman");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void startMenu(){
        frame.setContentPane(new gameMenu());
        frame.validate();
    }

    public static void startGameOptions(){
        frame.setContentPane(new gameOptions());
        frame.validate();
    }

    public static void startGame(){
        frame.setContentPane(new gamePlay());
        frame.validate();
    }

    public static void isInputValid(){
        if ((difficulty == 10 || difficulty == 8 || difficulty == 6) && !(secretWord.isEmpty())){
            startGame();
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a word and select a difficulty");
        }
    }

    public static void setDifficulty(int getDifficulty){
        //difficulty is a number equal to how many mistakes may be made. Easy = 10, Med = 8, Hard = 6.
        difficulty = getDifficulty;
    }
    public static int getDifficulty(){
        return difficulty;
    }

    public static void setSecretWord(String getSecretWord){
        secretWord = getSecretWord;
    }
    public static String getSecretWord(){
        return secretWord;
    }


}