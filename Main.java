package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;

public class Main extends JFrame {
    private static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame();
        frame.setSize(800,400);
        frame.setVisible(true);
        frame.setTitle("Hangman");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startMenu();
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
}
