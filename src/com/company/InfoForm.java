package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class InfoForm {
    private JPanel infoForm;
    private JTextPane MainTextPanel;
    private JButton returnMenu;
    private JLabel mainTitle;
    private JLabel footer;

    private JFrame infoFrame = new JFrame();

    public InfoForm() {
        returnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.dispose();
                MainGUI mainGUI = new MainGUI();
                mainGUI.load();
            }
        });
    }

    public void load() {
        infoFrame.add(infoForm);
        infoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        infoFrame.pack();
        infoFrame.setSize(500, 400);
        MainTextPanel.setFont(new Font("Verdana", Font.PLAIN, 16));
        MainTextPanel.setBackground(new Color(0,0,0, 0));
        infoFrame.setVisible(true);
    }
}
