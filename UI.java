package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class UI {

    private HospitalManager manager = new HospitalManager();

    public void start() {
        while (true) {
            int choice = showMenu();

            if (choice == 8) break;

            switch (choice) {
                case 0: addPatient(); break;
                case 1: deletePatient(); break;
                case 2: viewAll(); break;
                case 3: searchCondition(); break;
                case 4: viewEmergency(); break;
                case 5: lookupPatient(); break;
                case 6: undoDelete(); break;
                case 7: saveRecords(); break;
            }
        }
    }

    // ---------------------------------------------------------
    // CLEAN TWO‑ROW MENU WITH REAL BUTTONS (NO DUPLICATES)
    // ---------------------------------------------------------
    private int showMenu() {

        JDialog dialog = new JDialog();
        dialog.setTitle("Hospital Management System");
        dialog.setModal(true);
        dialog.setSize(600, 250);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 10));

        String[] labels = {
                "Add Patient",
                "Delete Patient",
                "View All Patients",
                "Search by Condition",
                "View Emergency",
                "Lookup by ID",
                "Undo Delete",
                "Save Records"
        };

        int[] result = {-1};

        for (int i = 0; i < labels.length; i++) {
            int index = i;
            JButton button = new JButton(labels[i]);
            button.addActionListener(e -> {
                result[0] = index;
                dialog.dispose();
            });
            panel.add(button);
        }

        dialog.add(panel);
        dialog.setVisible(true);

        return result[0];
    }

    // ---------------------------------------------------------
    // MENU ACTIONS
    // ---------------------------------------------------------

    private void addPatient() {
        String name = JOptionPane.showInputDialog("Enter name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter age:"));
        String gender = JOptionPane.showInputDialog("Enter gender:");
        String condition = JOptionPane.showInputDialog("Enter condition:");
        String hospital = JOptionPane.showInputDialog("Enter hospital:");

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
            return;
        }

        String admission = (severity.equals("RED") || severity.equals("ORANGE"))
                ? "Emergency"
                : "Standard";

        Patient p = new Patient(
                new Random().nextInt(1000),
                name, age, gender, condition,
                hospital, admission, 500, severity
        );

        manager.addPatient(p);
        JOptionPane.showMessageDialog(null, "Patient added successfully.");
    }

    private void deletePatient() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID to delete:"));
        manager.deletePatient(id);
        JOptionPane.showMessageDialog(null, "Delete attempted.");
    }

    private void viewAll() {
        JOptionPane.showMessageDialog(null, manager.viewAllPatients());
    }

    private void searchCondition() {
        String cond = JOptionPane.showInputDialog("Enter condition:");
        JOptionPane.showMessageDialog(null, manager.searchCondition(cond));
    }

    private void viewEmergency() {
        JOptionPane.showMessageDialog(null, manager.viewEmergencyPatients());
    }

    private void lookupPatient() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID:"));
        JOptionPane.showMessageDialog(null, manager.lookupPatient(id));
    }

    private void undoDelete() {
        manager.undoDelete();
        JOptionPane.showMessageDialog(null, "Undo complete.");
    }

    private void saveRecords() {
        manager.saveRecords();
        JOptionPane.showMessageDialog(null, "Records saved.");
    }
}
