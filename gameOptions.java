package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class gameOptions extends JPanel {
    private JLabel headLb, enterWordLb, selectDifLb;
    private JTextField enterWordTf;
    private JButton diffEasyBt, diffMedBt, diffHardBt, gameStartBt;
    private JPanel contentPane, optionsPane, wordPane, diffPane, diffButtonPn;
    public String wordS;

    public gameOptions(){
        setLabels();
        setTextField();
        setButtons();

        contentPane = new JPanel(new GridLayout(3,1));
        optionsPane = new JPanel(new BoxLayout(optionsPane, BoxLayout.PAGE_AXIS));

        wordPane = new JPanel(new BoxLayout(wordPane, BoxLayout.PAGE_AXIS));
        wordPane.add(enterWordLb);
        wordPane.add(enterWordTf);

        diffPane = new JPanel(new BoxLayout(diffPane, BoxLayout.PAGE_AXIS));
        diffPane.add(selectDifLb);
        diffPane.add(diffButtonPn);

        optionsPane.add(wordPane);
        optionsPane.add(diffPane);

        contentPane.add(headLb);
        contentPane.add(optionsPane);
        contentPane.add(gameStartBt);

        add(contentPane);
    }

    public void setLabels(){
        JPanel test = new JPanel();
        test.setLayout(new BoxLayout(test, BoxLayout.PAGE_AXIS));
        headLb = new JLabel("Options");
        test.add(headLb, BoxLayout.X_AXIS);
        headLb.setFont(new Font("Century Gothic", Font.BOLD, 30));

        enterWordLb = new JLabel("Enter the secret word");
        selectDifLb = new JLabel("Select the difficulty");

        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        labels.add(enterWordLb);
        labels.add(selectDifLb);

        for (JLabel label : labels){
            label.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        }
    }

    public void setTextField(){

        enterWordTf = new JTextField(20);
    }

    public void setButtons(){
        diffButtonPn = new JPanel(new GridLayout(1,3,10,0));
        diffEasyBt = new JButton("Easy");
        diffMedBt = new JButton("Medium");
        diffHardBt = new JButton("Hard");
        gameStartBt = new JButton("Start");
        diffButtonPn.add(diffEasyBt);
        diffButtonPn.add(diffMedBt);
        diffButtonPn.add(diffHardBt);
    }
}
