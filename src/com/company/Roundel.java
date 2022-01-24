package com.company;

import java.util.Scanner; // Import the Scanner class to read text files
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


public class Roundel {
    private String file_path, file_name;
    private int height, quantity; // first line of each file
    private int hole_diameter_count[]; // second line means the disc diameter of tower
    private int hole_diameter_in[]; // third line means the disc diameter to fit to the tower
    private int result;

    public Roundel() {
    }

    /**
     * Load date from file
     *
     * @param l_path relative path to the file
     */
    public Roundel(String l_path) {
        this.file_path = l_path;

        System.out.println();

        try {
            File file = new File(l_path);
            Scanner myReader = new Scanner(file);

            this.file_name = file.getName();

            System.out.println("Wczytuje plik: " + this.file_name);

            int line = 0, size = 0;
            while (myReader.hasNextLine()) {
                String data_line = myReader.nextLine();
                String[] numbers = data_line.split(" ");

                switch (line) {
                    case 0:
                        this.height = Integer.parseInt(numbers[0]);
                        this.quantity = Integer.parseInt(numbers[1]);
                        break;
                    case 1:
                        size = numbers.length;
                        this.hole_diameter_count = new int[size];
                        for (int i = 0; i < size; i++) {
                            this.hole_diameter_count[i] = Integer.parseInt(numbers[i]);
//                            System.out.println(this.hole_diameter_count[i]);
                        }
                        break;
                    case 2:
                        size = numbers.length;
                        this.hole_diameter_in = new int[size];
                        for (int i = 0; i < size; i++) {
                            this.hole_diameter_in[i] = Integer.parseInt(numbers[i]);
//                            System.out.println(this.hole_diameter_in[i]);
                        }
                        break;
                }

                line++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Calculate metod
     */
    public void calculate() {
        for (int i = 1; i < this.height; i++) {
            this.hole_diameter_count[i] = Math.min(this.hole_diameter_count[i], this.hole_diameter_count[i - 1]);
        }

        int iterator = this.height;

        for (int i = 0; i < this.quantity; i++) {
//            System.out.println(this.hole_diameter_in[i]);
            while (iterator > 0 && this.hole_diameter_count[iterator - 1] < this.hole_diameter_in[i]) {
                iterator--;
            }
            iterator--;
        }

        this.result = Math.max(0, iterator + 1);
    }

    /**
     * save output
     */
    public void saveOutput() {
        String output_path = "test_files\\out\\";
        File directory = new File(output_path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            FileWriter myWriter = new FileWriter(output_path + this.file_name);
            myWriter.write(Integer.toString(this.result));
            System.out.println(this.result);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Wynik operacji to: %d", this.result);
    }

}
