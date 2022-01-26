package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.util.ArrayList;

import javax.swing.table.*;

public class RounderForm {
    private JPanel rounderForm;
    private JTextField fileLocation;
    private JButton loadFiles;
    private JButton calculateAlg;
    private JButton showOutput;
    private JButton returnMenu;
    private JTable outputTable;
    private JButton saveOutput;
    private ArrayList<Roundel> roundelList = new ArrayList<Roundel>();

    private JFrame rounderFrame = new JFrame();

    public RounderForm() {
        returnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rounderFrame.dispose();
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

                roundelList.clear();
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        roundelList.add(new Roundel(file.getPath()));
                    }
                }
            }
        });
        calculateAlg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean success = true;
                    if (!roundelList.isEmpty()) {
                        for (Roundel roundel : roundelList) {
                            roundel.calculate();
                            String incorrectDataOnFile = roundel.incorrectDataOnFile();
                            if (incorrectDataOnFile != null) {
                                JOptionPane.showMessageDialog(rounderForm,
                                        "Nie poprawne dane w pliku: " + incorrectDataOnFile,
                                        "Błąd algorytmu",
                                        JOptionPane.WARNING_MESSAGE);

                                success = false;
                            }
                        }
                    }
                    else {
                        success = false;

                        JOptionPane.showMessageDialog(rounderForm,
                                "Nie wskazano poprawnego folderu!",
                                "Błąd algorytmu",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    if (success) {
                        JOptionPane.showMessageDialog(rounderForm,
                                "Algorytm został wykonany pomyślnie!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rounderForm,
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

                if (!roundelList.isEmpty()) {
                    model.addColumn("FileName");
                    model.addColumn("Output");

                    for (Roundel roundel : roundelList) {
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
                String output_path = "test_files\\out\\";
                if (!roundelList.isEmpty()) {
                    for (Roundel roundel : roundelList) {
                        roundel.saveOutput(output_path);
                    }
                    JOptionPane.showMessageDialog(rounderForm,
                            "Wyniki zapisane w katalogu: " + output_path);
                }
                else {
                    JOptionPane.showMessageDialog(rounderForm,
                            "Nie udało się zapisać wyniku!",
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void load() {
        rounderFrame.add(rounderForm);
        rounderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rounderFrame.pack();
        rounderFrame.setVisible(true);

        outputTable.setVisible(false);
    }
}
