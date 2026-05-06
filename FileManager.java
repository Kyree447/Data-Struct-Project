package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.LinkedList;

public class FileManager {

    // SAVE PATIENTS TO patients.txt
    public static void savePatients(LinkedList<Patient> patients) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("patients.txt"))) {

            if (patients.isEmpty()) {
                writer.println("No patients available.");
                return;
            }

            for (Patient p : patients) {
                writer.println(p.toString());
            }

        } catch (IOException e) {
            System.out.println("Error saving patient records: " + e.getMessage());
        }
    }

    // LOAD PATIENTS FROM patients.txt
    public static LinkedList<Patient> loadPatients() {
        LinkedList<Patient> list = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isBlank() && !line.equals("No patients available.")) {
                    list.add(Patient.fromString(line));
                }
            }

        } catch (IOException e) {
            System.out.println("No saved patient file found.");
        }

        return list;
    }
}
