package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main extends JFrame {
    public static JFrame frame;
    public static int difficulty;
    public static String secretWord;
    public static JButton easyBt, medBt, hardBt;
    public static ArrayList<String> singleEasy, singleMedium, singleHard;
    public static ArrayList<ArrayList> listList;
    public static String[] easyList, mediumList, hardList;

    public static void main(String[] args) {
        createFrame();
        startMenu();
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

    public static void startSinglePlayer(){
        gameDictionary();
        Random rand = new Random();
        int ranList = rand.nextInt(listList.size());
        int ranWord = rand.nextInt(singleEasy.size());
        int ranDif = rand.nextInt(3)+3;

        ArrayList<String> chosenList = listList.get(ranList);
        String chosenWord = chosenList.get(ranWord);

        setSecretWord(chosenWord.toUpperCase());
        System.out.println("List: " + chosenList + " -- Word: " + chosenWord);
        setDifficulty(ranDif*2);

        System.out.println(secretWord);
        System.out.println(difficulty);

        startGame();
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

    public static void gameDictionary(){
        singleEasy = new ArrayList<>();
        easyList = new String[] {"advice", "border", "donkey", "film", "kids", "nurse", "shout", "zoo", "shake", "stairs", "friendly"};
        singleEasy.addAll(Arrays.asList(easyList));

        singleMedium = new ArrayList<>();
        mediumList = new String[] {"practical", "skeleton", "memory", "fireplace", "damage", "grandmother", "instantly", "officially", "satellite", "shallow", "wealthy"};
        singleMedium.addAll(Arrays.asList(mediumList));

        singleHard = new ArrayList<>();
        hardList = new String[] {"remarkable", "principally", "abyss", "jazz", "microwave", "quantum", "blizzard", "espionage", "luxury", "oxygen", "whiskey"};
        singleHard.addAll(Arrays.asList(hardList));

        listList = new ArrayList<>();
        listList.add(singleEasy);
        listList.add(singleMedium);
        listList.add(singleHard);
    }

    public static void setDifficulty(int getDifficulty){
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