package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class gamePlay extends JPanel {
    public static int difficulty;
    private int mistakes;

    public String secretWord;
    public char guessedChar;
    public ArrayList<Character> secretChars;
    public ArrayList<JLabel> charJbList, mistakesArray;

    public JLabel guessLb, mistakesLb, secretChar;
    public JTextField guessTf;
    private JPanel contentPane, hangPane, drawPane, playPane, guessPane, guessBoxPane, mistakePane, mistakeCharsPane, charsPane;

    public gamePlay(){

        new drawComponent();

        gameGetDifficulty();
        gameGetSecretWord();

        mistakesArray = new ArrayList<>();
        mistakes = difficulty;

        setPanels();
        setLabels();

        guessTf.grabFocus();
        guessTf.requestFocus();

        mistakePane.add(mistakesLb, BorderLayout.PAGE_START);
        guessPane.add(guessLb);

        add(contentPane);
        Main.frame.revalidate();
        Main.frame.repaint();
        Main.frame.setVisible(true);
    }

    public void paint(Graphics g){
        System.out.println("In paint method");
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.black);

        if (mistakes <= 9){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(25,getHeight()-40,325,getHeight()-40); //done
        }if (mistakes <= 8){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(140,getHeight()-40,140,getHeight()-240); // done
        }if (mistakes <= 7){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(140,getHeight()-240,260,getHeight()-240); //done
        }if (mistakes <= 6){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(260,getHeight()-240,260,getHeight()-200); //done
        }if (mistakes <= 5){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawOval(247,getHeight()-200,26,26);
        }if (mistakes <= 4){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(260,getHeight()-174,260,getHeight()-120);
        }if (mistakes <= 3){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(260,getHeight()-174,220,getHeight()-140);
        }if (mistakes <= 2){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(260,getHeight()-174,300,getHeight()-140);
        }if (mistakes <= 1){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(260,getHeight()-120,300,getHeight()-70);
        }if (mistakes <= 0){
            System.out.println("Paint <= 9 mistakes");
            g2d.drawLine(260,getHeight()-120,220,getHeight()-70);
        }


        g2d.dispose();
    }

    public void actionPerformed(ActionEvent e){
        System.out.println("act perf");
        this.repaint();
    }

    ArrayList<Character> correctGuesses = new ArrayList<>();
    String hasWon = "";

    public void gameGuess(char guess){
        String guessStr = "" + guess;
        guessStr = guessStr.toUpperCase();

        correctGuesses.add(guess);

        for (char corrGuess: correctGuesses) {
            hasWon = hasWon+ corrGuess;
            System.out.println(hasWon);
        }


        int cycle = 0;
        for (char c : secretChars){
            String charStr = "" + c;

            if (charStr.equals(guessStr)){
                charJbList.get(cycle).setFont(new Font("Century Gothic", Font.BOLD, 30));
            }
            cycle++;
        }

        for (JLabel mis : mistakesArray){
            mis.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        }

        System.out.print(mistakes);

    }

    public void gameMistake(char guess){
        String guessStr = "" + guess;
        guessStr = guessStr.toUpperCase();
        JLabel mistakeCharLb = new JLabel();

        for (JLabel mis : mistakesArray){
            if (mis.getText().equals(guessStr)){
                mis.setBorder(BorderFactory.createDashedBorder(Color.RED, 2, 2));
                return;
            } else {
                mis.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); //removes border from selected char
            }
        }

        mistakeCharLb.setText(guessStr);
        mistakeCharLb.setFont(new Font("Century Gothic", Font.BOLD, 20));
        mistakeCharLb.setHorizontalAlignment(SwingConstants.CENTER);
        mistakeCharLb.setVerticalAlignment(SwingConstants.CENTER);
        mistakeCharLb.setPreferredSize(new Dimension(30,30));

        mistakesArray.add(mistakeCharLb);
        mistakes--;
        this.repaint();

        if (mistakes == 0){
            JOptionPane.showMessageDialog(new JFrame(),"Game Over! \n Return to main menu");
            Main.startMenu();
        }

        for (JLabel mis : mistakesArray){
            mistakeCharsPane.add(mis);
            //mistakeCharsPane.add(new JLabel("--"));
            mistakeCharsPane.setVisible(true);
        }



        mistakesArray.add(new JLabel("--"));
        mistakesLb.setText("Mistakes : (" + mistakes + ")");
    }


    public void setLabels(){
        guessLb = new JLabel("Guess: ");
        guessLb.setHorizontalAlignment(SwingConstants.CENTER);
        guessLb.setFont(new Font("Century Gothic", Font.BOLD, 20));

        mistakesLb = new JLabel("Mistakes : (" + mistakes + ")");
        mistakesLb.setHorizontalAlignment(SwingConstants.CENTER);
        mistakesLb.setFont(new Font("Century Gothic", Font.BOLD, 20));

        secretChars = new ArrayList<>();
        charJbList = new ArrayList<>();

        int charWidth = (int) ((1.0/secretWord.length()) * 300); //Makes the width of the character positions relative to the number of characters in the secretWord.

        for (char c : secretWord.toCharArray()){
            secretChars.add(c);
            secretChar = new JLabel();
            charJbList.add(secretChar);
            secretChar.setHorizontalAlignment(SwingConstants.CENTER);
            secretChar.setFont(new Font("Century Gothic", Font.BOLD, 0));
            secretChar.setText("" + c);
            secretChar.setPreferredSize(new Dimension(charWidth,(charWidth+10)));
            secretChar.setBorder(BorderFactory.createMatteBorder(0,0,3,0, Color.decode("#2b2b2b")));
            charsPane.add(secretChar);
        }
    }

    public void setPanels(){
        contentPane = new JPanel(new BorderLayout(4,0)); //holds all other parent panes
        contentPane.setOpaque(true);

        hangPane = new JPanel(new BorderLayout()); //Holds the drawing field
        hangPane.setPreferredSize(new Dimension(330,350));
        hangPane.setOpaque(true);
        hangPane.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));

        playPane = new JPanel(new BorderLayout(0,50)); //Holds guess, mistake en charsPane.
        playPane.setPreferredSize(new Dimension(430, 350));
        playPane.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));


        guessPane = new JPanel(new BorderLayout()); //holds guess label and textfield;
        guessBoxPane = new JPanel();

        guessTf = new JTextField(){
            @Override
            public void addNotify(){
                super.addNotify();
                requestFocus();
            }
        };

        guessTf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if(Character.isLowerCase(keyChar)){
                    e.setKeyChar(Character.toUpperCase(keyChar));
                }

                int maxCharacters = 1;
                guessTf.getCaret().setVisible(false);
                if (guessTf.getText().length() >= maxCharacters){
                    e.consume();
                }
            }
        });

        guessTf.addActionListener(e -> {
            if (guessTf.getText().length() >= 2 || guessTf.getText().length() <= 0){
                JOptionPane.showMessageDialog(new JFrame(), "Please enter 1 character.");
                guessTf.setText("");
            } else {
                char guessedChar = guessTf.getText().charAt(0);
                String guessedString = guessTf.getText();
                guessedString = guessedString.toUpperCase();

                System.out.println("before loop " + secretWord + " =?= " + guessedString);
                if (secretWord.contains(guessedString)){
                    gameGuess(guessedChar);
                } else {
                    gameMistake(guessedChar);
                }
                guessTf.setText("");
            }
        });
        guessTf.setFont(new Font("Century Gothic", Font.BOLD, 40));
        guessTf.requestFocus();

        guessTf.setPreferredSize(new Dimension(80,80));
        guessTf.setHorizontalAlignment(SwingConstants.CENTER);
        guessTf.setBackground(Color.decode("#e0e0e0"));
        guessTf.setBorder(BorderFactory.createMatteBorder(3,0,3,0, Color.BLACK));

        guessBoxPane.add(guessTf);
        guessPane.add(guessBoxPane, BorderLayout.PAGE_END);

        mistakePane = new JPanel(new BorderLayout(10,0)); //holds mistakes label and mistakes chars
        mistakeCharsPane = new JPanel();
        mistakeCharsPane.setPreferredSize(new Dimension(330,50));

        drawPane = new JPanel();
        drawPane.setBackground(Color.white);

        mistakePane.add(mistakeCharsPane, BorderLayout.CENTER);

        charsPane = new JPanel(); //Holds all chars in the secret word.

        hangPane.add(mistakePane, BorderLayout.PAGE_START);
        hangPane.add(drawPane, BorderLayout.CENTER);

        playPane.add(guessPane, BorderLayout.PAGE_START);
        playPane.add(charsPane, BorderLayout.PAGE_END);

        contentPane.add(hangPane, BorderLayout.WEST);
        contentPane.add(playPane, BorderLayout.EAST);
    }


    public void gameGetDifficulty(){
        difficulty = Main.getDifficulty();
    }

    public void gameGetSecretWord(){
        secretWord = Main.getSecretWord();
    }
}

class drawComponent extends JPanel{



    public Dimension getPrefferedSize(){
        return new Dimension(800,400);
    }

}