package org.example;

import java.io.*;
import java.util.LinkedList;

public class FileManager {

    public static void savePatients(
            LinkedList<Patient> patients) {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter("patients.txt"))) {

            for (Patient p : patients) {
                writer.println(p.toString());
            }

            System.out.println("Patients saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}
