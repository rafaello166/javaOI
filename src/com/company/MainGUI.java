package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JPanel mainForm;
    private JButton roundelAlg;
    private JButton taxiAlg;
    private JButton infoButton;
    private JButton exitButton;

    private JFrame mainFrame = new JFrame();

    public void load() {
        mainFrame.add(mainForm);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public MainGUI() {
        roundelAlg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                AlgorithmForm rounderForm = new AlgorithmForm();
                rounderForm.load("Rounder");
            }
        });
        taxiAlg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                AlgorithmForm rounderForm = new AlgorithmForm();
                rounderForm.load("Taxi");
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                InfoForm infoForm = new InfoForm();
                infoForm.load();
            }
        });
    }

}
