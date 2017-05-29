package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;

public class Main extends JFrame {
    private static JFrame frame;
    public static int difficulty;
    public static String secretWord;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setSize(800,400);
        frame.setVisible(true);
        frame.setTitle("Hangman");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //startMenu();
        startGameOptions();
    }

    public static void startMenu(){
        System.out.print("menu method");
        frame.setContentPane(new gameMenu());
        frame.validate();
    }

    public static void startGameOptions(){
        System.out.print("start options");
        frame.setContentPane(new gameOptions());
        frame.validate();
    }

    public static void startGame(){
        frame.setContentPane(new gamePlay());
        frame.validate();
    }

    public static void isInputValid(){
        System.out.print("isInputValid? ---- ");
        System.out.print("diff: " + difficulty + " word: " + secretWord);
        if ((difficulty == 10 || difficulty == 8 || difficulty == 6) && !(secretWord.isEmpty())){
            startGame();
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a word and select a difficulty");
        }
    }

    public static int setDifficulty(int getDifficulty){
        //difficulty is a number equal to how many mistakes may be made. Easy = 10, Med = 8, Hard = 6.
        difficulty = getDifficulty;
        return difficulty;
    }

    public static String setSecretWord(String getSecretWord){
        secretWord = getSecretWord;
        return secretWord;
    }
}