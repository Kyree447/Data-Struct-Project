package org.example;

import javax.swing.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        HospitalManager manager = new HospitalManager();

        String[] options = {
                "Add Patient",
                "Delete Patient",
                "View All Patients",
                "Search Patient",
                "Search By Condition",
                "View Emergency Patients",
                "Lookup Patient Name by ID",   // NEW
                "Undo Delete",
                "Save Records",
                "Exit"
        };

        while (true) {

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Hospital Management System",
                    "Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == -1 || choice == 8) break;

            switch (choice) {

                case 0: // Add Patient
                    String name = JOptionPane.showInputDialog("Enter name:");
                    int age = Integer.parseInt(JOptionPane.showInputDialog("Enter age:"));
                    String gender = JOptionPane.showInputDialog("Enter gender:");
                    String condition = JOptionPane.showInputDialog("Enter condition:");
                    String hospital = JOptionPane.showInputDialog("Enter hospital:");

                    // Severity dropdown
                    String[] severityOptions = {"RED", "ORANGE", "YELLOW", "GREEN"};
                    String severity = (String) JOptionPane.showInputDialog(
                            null,
                            "Select Severity:",
                            "Severity",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            severityOptions,
                            severityOptions[0]
                    );

                    if (severity == null) {
                        JOptionPane.showMessageDialog(null, "No severity selected.");
                        break;
                    }

                    // Auto-assign admission type
                    String admission = (severity.equals("RED") || severity.equals("ORANGE"))
                            ? "Emergency"
                            : "Standard";

                    Patient p = new Patient(
                            new Random().nextInt(1000),
                            name, age, gender, condition,
                            hospital, admission, 500, severity
                    );

                    manager.addPatient(p);
                    JOptionPane.showMessageDialog(null, "Patient added.");
                    break;

                case 1: // Delete Patient
                    int delId = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID:"));
                    manager.deletePatient(delId);
                    JOptionPane.showMessageDialog(null, "Delete attempted.");
                    break;

                case 2: // View All Patients
                    JOptionPane.showMessageDialog(null, manager.viewAllPatients());
                    break;

                case 3: // Search Patient
                    int searchId = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID:"));
                    JOptionPane.showMessageDialog(null, manager.searchPatient(searchId));
                    break;

                case 4: // Search Condition
                    String cond = JOptionPane.showInputDialog("Enter condition:");
                    JOptionPane.showMessageDialog(null, manager.searchCondition(cond));
                    break;

                case 5: // Emergency Queue
                    JOptionPane.showMessageDialog(null, manager.viewEmergencyPatients());
                    break;

                case 6: // Lookup Patient Name by ID
                    int lookupId = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID:"));
                    JOptionPane.showMessageDialog(null, manager.lookupPatientName(lookupId));
                    break;

                case 7: // Undo Delete
                    manager.undoDelete();
                    JOptionPane.showMessageDialog(null, "Undo complete.");
                    break;

                case 8: // Save
                    manager.saveRecords();
                    JOptionPane.showMessageDialog(null, "Records saved.");
                    break;
            }
        }
    }
}
