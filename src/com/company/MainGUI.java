package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JPanel mainForm;
    private JButton roundel;
    private JButton XXXButton;
    private JButton informacjeButton;
    private JButton wyjścieButton;

    private JFrame mainFrame = new JFrame();

    public void load() {
        mainFrame.add(mainForm);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public MainGUI() {
        roundel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                RounderForm rounderForm = new RounderForm();
                rounderForm.load();
            }
        });
        wyjścieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
