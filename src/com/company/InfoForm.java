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
        mainTitle.setFont(new Font("Verdana", Font.PLAIN, 26));
        mainTitle.setForeground(new Color(154, 101, 12));
        footer.setFont(new Font("Verdana", Font.PLAIN, 20));
        footer.setForeground(new Color(130, 100, 50, 255));
        MainTextPanel.setFont(new Font("Verdana", Font.PLAIN, 16));
        MainTextPanel.setBackground(new Color(242, 242, 242));
        infoFrame.setVisible(true);
    }
}
