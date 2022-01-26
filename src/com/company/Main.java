package com.company;

import java.util.Scanner;
import java.util.concurrent.TimeUnit; // for delay
import java.lang.InterruptedException;
import java.io.File;


public class Main {
    public static void main(String[] args) {

        // Uruchamianie GUI
        MainGUI mainGUI = new MainGUI();
        mainGUI.load();

        // main menu
//        System.out.println("----");
//        System.out.println("MENU");
//        System.out.println("----");
//
//        System.out.println("1. Krążki Olimpiada Informatyczna");
//        System.out.println("2. X");
//        System.out.println("3. README");
//        System.out.println("4. Koniec!");
//        System.out.println();
//
//
//        Scanner scan = new Scanner(System.in);
//        int step = scan.nextInt();
//        int step = 2;
//
//        switch (step) {
//            case 1:
//                File folder = new File("test_files/in/");
//                File[] listOfFiles = folder.listFiles();
//
//                for (File file : listOfFiles) {
//                    if (file.isFile()) {
//                        Roundel roundel = new Roundel(file.getPath());
//
//                        roundel.calculate();
//
//                        roundel.saveOutput("test_files\\out\\");
//
//                        System.out.println(roundel);
//
//                    }
//                }
//
//                break;
//            case 2:
//                File taxiFolder = new File("test_files/tak/in/");
//                File[] listOfTaxiFiles = taxiFolder.listFiles();
//
//                for (File file : listOfTaxiFiles) {
//                    if (file.isFile()) {
//                        Taxi taxi = new Taxi(file.getPath());
//
//                        taxi.calculate();
//
//                        taxi.saveOutput("test_files\\tak\\out\\");
//
//                        System.out.println(taxi);
//
//                    }
//                }
//            default:
//                System.exit(0);
//                break;
//        }

    }
}
