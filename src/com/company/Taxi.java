package com.company;

import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors


public class Taxi extends Roundel {
    private long start_end_distance, start_point_distance, taxisNumber; // [m,d,n] distance in km
    private long fuel_distance[]; // [xi]

    public Taxi() {
    }

    /**
     * Load date from file
     *
     * @param l_path relative path to the file
     */
    public Taxi(String l_path) {
        this.file_path = l_path;
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
                        this.start_end_distance = tryParseLong(numbers[0]);
                        this.start_point_distance = tryParseLong(numbers[1]);
                        this.taxisNumber = tryParseLong(numbers[2]);
                        break;
                    case 1:
                        size = numbers.length;
                        this.fuel_distance = new long[size];
                        for (int i = 0; i < size; i++) {
                            this.fuel_distance[i] = tryParseLong(numbers[i]);
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
     * parseLong with exception method
     *
     * @param String text
     * @return Long
     */
    private Long tryParseLong(String text) {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            this.incorrectData = true;
            return -1L;
        }
    }

    /**
     * Desc sort long array
     *
     * @param arr
     */
    public void sortLongArrDesc(long arr[]) {
        Arrays.sort(arr);
        long k, t;
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            t = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = t;
        }

    }

    /**
     * Calculate metod
     */
    public void calculate() {
        if (this.incorrectData) {
            this.result = -1L;
            return;
        }

        sortLongArrDesc(fuel_distance);

        if (this.fuel_distance[0] < start_end_distance - start_point_distance) {
            this.result = 0;
            return;
        }

        int l = 0;
        int k = 0;
        while (k + 1 < this.taxisNumber && this.fuel_distance[k + 1] >= this.start_end_distance - this.start_point_distance) {
            k++;
        }

        long lastTaxi = this.fuel_distance[1];

        while (k + 1 < this.taxisNumber) {
            this.fuel_distance[k] = this.fuel_distance[k + 1];
            k++;
        }
        this.taxisNumber--;

        long position = 0;

        for (int i = 0; i < this.taxisNumber; ++i) {
            if (2L * (this.start_point_distance - position) + this.start_end_distance - this.start_point_distance <= lastTaxi) {
                this.result = i + 1;
                return;
            }

            if (this.start_point_distance - position > this.fuel_distance[i]) {
                this.result = 0;
                return;
            }

            position += (this.fuel_distance[i] - (this.start_point_distance - position));
            if (position >= this.start_end_distance) {
                this.result = i + 1;
                return;
            }
            if (position > this.start_point_distance) {
                position = this.start_point_distance;
            }
        }

        if (2L * (this.start_point_distance - position) + this.start_end_distance - this.start_point_distance <= lastTaxi) {
            this.result = this.taxisNumber + 1L;
            return;
        }

        this.result = 0;
    }

}
