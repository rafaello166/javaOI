package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import javax.swing.table.*;

public class AlgorithmForm {
    private JPanel algorithmForm;
    private JTextField fileLocation;
    private JButton loadFiles;
    private JButton calculateAlg;
    private JButton showOutput;
    private JButton returnMenu;
    private JTable outputTable;
    private JButton saveOutput;
    private JLabel algorithmName;
    private JLabel mainTitle;
    private ArrayList<Roundel> instanceAlgoritm = new ArrayList<Roundel>();
    private String roundelOrTaxiClass; // Class name to override

    private JFrame algorithmFrame = new JFrame();

    public AlgorithmForm() {
        returnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                algorithmFrame.dispose();
                MainGUI mainGUI = new MainGUI();
                mainGUI.load();
            }
        });
        loadFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Wybierz folder do wczytania plików (*.txt):");
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    if (jfc.getSelectedFile().isDirectory()) {
                        System.out.println("You selected the directory: " + jfc.getSelectedFile());
                        fileLocation.setText(jfc.getSelectedFile().toString());
                    } else {
                        return;
                    }
                }

                File folder = new File(jfc.getSelectedFile().toString());
                File[] listOfFiles = folder.listFiles();

                instanceAlgoritm.clear();
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        if (roundelOrTaxiClass == "Roundel") {
                            instanceAlgoritm.add(new Roundel(file.getPath()));
                        }
                        else if (roundelOrTaxiClass == "Taxi") {
                            instanceAlgoritm.add(new Taxi(file.getPath()));
                        }
                    }
                }
            }
        });
        calculateAlg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int successFiles = 0;
                    if (!instanceAlgoritm.isEmpty()) {
                        for (Roundel roundel : instanceAlgoritm) {
                            roundel.calculate();
                            String incorrectDataOnFile = roundel.incorrectDataOnFile();
                            if (incorrectDataOnFile != null) {
                                JOptionPane.showMessageDialog(algorithmForm,
                                        "Nie poprawne dane w pliku: " + incorrectDataOnFile,
                                        "Błąd algorytmu",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                            else{
                                successFiles++;
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(algorithmForm,
                                "Nie wskazano poprawnego folderu!",
                                "Błąd algorytmu",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    if (successFiles > 0) {
                        JOptionPane.showMessageDialog(algorithmForm,
                                "Algorytm został wykonany poprawnie dla: " + successFiles + " plików");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(algorithmForm,
                            "Nie wskazano poprawnego folderu!",
                            "Błąd algorytmu",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        showOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                Map<String, Integer> resultAlgorithm = new HashMap<String, Integer>();

                outputTable.setVisible(true);

                DefaultTableModel model = (DefaultTableModel) outputTable.getModel();
                model.setColumnCount(0);
                model.setRowCount(0);

                if (!instanceAlgoritm.isEmpty()) {
                    model.addColumn("FileName");
                    model.addColumn("Output");

                    for (Roundel roundel : instanceAlgoritm) {
//                    resultAlgorithm.put(roundel.getResultOnFile()[0],
//                            Integer.parseInt(roundel.getResultOnFile()[1])
//                    );
                        model.addRow(new Object[]{
                                roundel.getResultOnFile()[0],
                                roundel.getResultOnFile()[1]
                        });
                    }
                }
                else {
                    model.addColumn("Error");
                    model.addRow(new Object[]{"Algorytm nie został wykonany!"});
                }
                outputTable = new JTable(model);
            }
        });
        saveOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String output_path = "output\\";
                if (!instanceAlgoritm.isEmpty()) {
                    for (Roundel roundel : instanceAlgoritm) {
                        roundel.saveOutput(output_path);
                    }
                    JOptionPane.showMessageDialog(algorithmForm,
                            "Wyniki zapisane w katalogu: " + output_path);
                }
                else {
                    JOptionPane.showMessageDialog(algorithmForm,
                            "Nie udało się zapisać wyniku!",
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    public void load(String roundelOrTaxiClass) {
        algorithmFrame.add(algorithmForm);
        algorithmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        algorithmFrame.pack();
        algorithmFrame.setSize(400, 500);
        mainTitle.setFont(new Font("Verdana", Font.PLAIN, 26));
        mainTitle.setForeground(new Color(154, 101, 12));
        algorithmName.setFont(new Font("Verdana", Font.PLAIN, 16));
        algorithmName.setForeground(new Color(120, 90, 25));
        algorithmFrame.setVisible(true);

        this.roundelOrTaxiClass = roundelOrTaxiClass;
        if (roundelOrTaxiClass == "Roundel") {
            algorithmName.setText("Krążki - Olimpiada Informatyczna XIII");
        }
        else {
            algorithmName.setText("Taksówki - Olimpiada Informatyczna XX");
        }
        outputTable.setVisible(false);
    }
}
