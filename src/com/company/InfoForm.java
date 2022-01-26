package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoForm {
    private JPanel infoForm;
    private JTextPane MainTextPanel;
    private JButton returnMenu;

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
        infoFrame.setVisible(true);
    }
}
