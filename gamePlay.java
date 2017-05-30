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
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(6));
        g2d.setColor(Color.decode("#2b2b2b"));

        if (mistakes <= 9){
            g2d.drawLine(25,getHeight()-20,325,getHeight()-20); // BASE
        }if (mistakes <= 8){
            g2d.drawLine(140,getHeight()-20,140,getHeight()-220); // POLE
        }if (mistakes <= 7){
            g2d.drawLine(140,getHeight()-220,260,getHeight()-220); // SUPPORT ROPE POLE
        }if (mistakes <= 6){
            g2d.drawLine(260,getHeight()-220,260,getHeight()-180); // ROPE
        }if (mistakes <= 5){
            g2d.fillOval(247,getHeight()-180,26,26); // HEAD

        }if (mistakes <= 4){
            g2d.drawLine(260,getHeight()-154,260,getHeight()-100); //TORSO
        }if (mistakes <= 3){
            g2d.drawLine(260,getHeight()-154,250,getHeight()-125); // LEFT ARM
            g2d.drawLine(250,getHeight()-125,250,getHeight()-100); // LEFT UNDERARM
        }if (mistakes <= 2){
            g2d.drawLine(260,getHeight()-154,270,getHeight()-125); // RIGHT ARM
            g2d.drawLine(270,getHeight()-125,270,getHeight()-100); // RIGHT UNDERARM
        }if (mistakes <= 1){
            g2d.drawLine(260,getHeight()-100,250,getHeight()-75); // LEFT LEG
            g2d.drawLine(250,getHeight()-75,250,getHeight()-50); // LEFT UNDERLEG
        }if (mistakes <= 0){
            g2d.drawLine(260,getHeight()-100,270,getHeight()-75); // RIGHT LEG
            g2d.drawLine(270,getHeight()-75,270,getHeight()-50); // LEFT UNDERLEG
        }

        g2d.dispose();
    }

    public void actionPerformed(ActionEvent e){
        this.repaint();
    }

    ArrayList<Character> correctGuesses = new ArrayList<>();
    String hasWon = "";

    public void gameGuess(char guess){
        String guessStr = "" + guess;
        guessStr = guessStr.toUpperCase();

        correctGuesses.add(guess);
        ArrayList<Character> testWin = new ArrayList<>();

        System.out.println("test corr: " + correctGuesses);
        System.out.println("test sec: " + secretChars);

        for (char corrGuess: correctGuesses) {
            hasWon = hasWon+ corrGuess;
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

        if (correctGuesses.containsAll(secretChars)) {
            JOptionPane.showMessageDialog(Main.frame, "Congratulations, you won!");
            Main.startMenu();
        }


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

        for (JLabel mis : mistakesArray){
            mistakeCharsPane.add(mis);
            //mistakeCharsPane.add(new JLabel("--"));
            mistakeCharsPane.setVisible(true);
        }

        mistakesArray.add(new JLabel("--"));
        mistakesLb.setText("Mistakes : (" + mistakes + ")");

        if (mistakes == 0){
            JOptionPane lossOpt = new JOptionPane();
            lossOpt.showMessageDialog(Main.frame,"Game Over! \n Return to main menu");
            mistakesLb.setText("Game over");
            Main.startMenu();
        }
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

        hangPane = new JPanel(new BorderLayout()); //Holds the drawing field
        hangPane.setPreferredSize(new Dimension(330,350));
        hangPane.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        playPane = new JPanel(new BorderLayout(0,50)); //Holds guess, mistake en charsPane.
        playPane.setPreferredSize(new Dimension(430, 350));


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
                JOptionPane.showMessageDialog(Main.frame, "Please enter 1 character.");
                guessTf.setText("");
            } else {
                char guessedChar = guessTf.getText().charAt(0);
                String guessedString = guessTf.getText();
                guessedString = guessedString.toUpperCase();

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

        JPanel buttonPane = new JPanel();

        JButton menuBtn = new JButton("Menu");
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.startMenu();
            }
        });
        menuBtn.setHorizontalAlignment(SwingConstants.CENTER);
        menuBtn.setPreferredSize(new Dimension(200,25));
        menuBtn.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        menuBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK,1, true));
        buttonPane.add(menuBtn);

        guessPane.add(buttonPane, BorderLayout.PAGE_START);

        mistakePane = new JPanel(new BorderLayout(10,0)); //holds mistakes label and mistakes chars
//        mistakePane.setBackground(Color.decode("#ACF0F2"));
        mistakeCharsPane = new JPanel();
//        mistakeCharsPane.setBackground(Color.decode("#ACF0F2"));
        mistakeCharsPane.setPreferredSize(new Dimension(330,50));

        drawPane = new JPanel();
//        drawPane.setBackground(Color.decode("#ACF0F2"));

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