package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class UI {

    private HospitalManager manager = new HospitalManager();

        public void start () {
            while (true) {
                String menu =
                        " Hospital Managment System \n" +
                                "1. Add Patient\n" +
                                "2. Delete Patient\n" +
                                "3. Lookup Patient\n" +
                                "4. View All Patients\n" +
                                "5. View Emergency Patients\n" +
                                "6. Undo Delete\n" +
                                "7. Save Records\n" +
                                "8. Exit\n\n" +
                                "Enter choice:";

                String choice = JOptionPane.showInputDialog(menu);
                if (choice == null) return;

                switch (choice) {

                    case "1":  // ADD PATIENT
                        String idInput = JOptionPane.showInputDialog("Enter patient ID:");
                        if (idInput == null) break;

                        int id;
                        try {
                            id = Integer.parseInt(idInput.trim());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ID must be a number.");
                            break;
                        }

                        String name = JOptionPane.showInputDialog("Enter name:");
                        if (name == null) break;

                        String ageInput = JOptionPane.showInputDialog("Enter age:");
                        if (ageInput == null) break;

                        int age;
                        try {
                            age = Integer.parseInt(ageInput.trim());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Age must be a number.");
                            break;
                        }

                        String gender = JOptionPane.showInputDialog("Enter gender:");
                        if (gender == null) break;

                        String condition = JOptionPane.showInputDialog("Enter condition:");
                        if (condition == null) break;

                        String hospital = JOptionPane.showInputDialog("Enter hospital:");
                        if (hospital == null) break;

                        String admission = JOptionPane.showInputDialog("Enter admission type:");
                        if (admission == null) break;

                        String billingInput = JOptionPane.showInputDialog("Enter billing amount:");
                        if (billingInput == null) break;

                        double billing;
                        try {
                            billing = Double.parseDouble(billingInput.trim());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Billing must be a number.");
                            break;
                        }

                        String severity = JOptionPane.showInputDialog("Enter severity (GREEN/YELLOW/ORANGE/RED):");
                        if (severity == null) break;

                        Patient p = new Patient(id, name, age, gender, condition, hospital, admission, billing, severity);
                        manager.addPatient(p);

                        JOptionPane.showMessageDialog(null, "Patient added.");
                        break;

                    case "2":  // DELETE
                        String delInput = JOptionPane.showInputDialog("Enter ID to delete:");
                        if (delInput == null) break;

                        try {
                            int delId = Integer.parseInt(delInput.trim());
                            manager.deletePatient(delId);
                            JOptionPane.showMessageDialog(null, "Patient deleted.");
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ID must be a number.");
                        }
                        break;

                    case "3":  // LOOKUP
                        String lookupInput = JOptionPane.showInputDialog("Enter ID to lookup:");
                        if (lookupInput == null) break;

                        try {
                            int lookupId = Integer.parseInt(lookupInput.trim());
                            JOptionPane.showMessageDialog(null, manager.lookupPatient(lookupId));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ID must be a number.");
                        }
                        break;

                    case "4":
                        JOptionPane.showMessageDialog(null, manager.viewAllPatients());
                        break;

                    case "5":
                        JOptionPane.showMessageDialog(null, manager.viewEmergencyPatients());
                        break;

                    case "6":
                        manager.undoDelete();
                        JOptionPane.showMessageDialog(null, "Undo complete.");
                        break;

                    case "7":
                        manager.saveRecords();
                        JOptionPane.showMessageDialog(null, "Records saved.");
                        break;

                    case "8":
                        JOptionPane.showMessageDialog(null, "Goodbye.");
                        return;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice.");
                        break;
                }
            }
        }

    public HospitalManager getManager() {
            return manager;
    }
}

