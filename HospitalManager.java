package org.example;

import java.util.*;

public class HospitalManager {

    private LinkedList<Patient> patients = new LinkedList<>();
    private Stack<Patient> deletedPatients = new Stack<>();
    private HashMap<Integer, Patient> patientDirectory = new HashMap<>();
    private PriorityQueue<Patient> emergencyQueue = new PriorityQueue<>((p1, p2) ->
                    p1.getSeverityLevel() - p2.getSeverityLevel());

    public HospitalManager() {
        patients = FileManager.loadPatients();

        // rebuilds hash map and priority queue on startup
        for (Patient p : patients) {
            patientDirectory.put(p.getId(), p);

            if (p.getSeverityLevel() <= 1) {
                emergencyQueue.add(p);
            }
        }
    }

    // method to add patient loads them to file if severity is oranger or red adds them to priority queue generates
    public void addPatient(Patient patient) {
        patients.add(patient);
        if (patient.getSeverityLevel() <= 1) {
            emergencyQueue.add(patient);
        }
        patientDirectory.put(patient.getId(), patient);
    }

    // method to delete patient iterates through linked list til id = id and pushes patient to stack for undo and deltes from all data structures and returns
    public void deletePatient(int id) {
        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getId() == id) {

                deletedPatients.push(p);
                iterator.remove();
                patientDirectory.remove(id);
                emergencyQueue.remove(p);

                return;
            }
        }
    }

    // method to undo delete if stack is not empty pushes top of stack and restores to data structres
    public void undoDelete() {
        if (!deletedPatients.isEmpty()) {
            Patient restored = deletedPatients.pop();

            patients.add(restored);
            patientDirectory.put(restored.getId(), restored);

            if (restored.getSeverityLevel() <= 1) {
                emergencyQueue.add(restored);
            }
        }
    }

    // method to look up by id if hashmap is not empty return id entered by user else no id found
    public String lookupPatient(int id) {
        Patient p = patientDirectory.get(id);
        if (p != null) {
            return p.toString();
        } else {
            return "No patient found with that ID.";
        }
    }

    // method to view patients if patients is not empty print statment else view patients
    public String viewAllPatients() {
        if (patients.isEmpty()) {
            System.out.print( "No patients available.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Patient p : patients) sb.append(p).append("\n");
            System.out.print(sb.toString());
        }
        return "";
    }

    // method to view patients if emergency queue is empty statment else print patient info
    public String viewEmergencyPatients() {
        if (emergencyQueue.isEmpty()) {
            return "No emergency patients.";
        } else {
            StringBuilder sb = new StringBuilder();
            PriorityQueue<Patient> copy = new PriorityQueue<>(emergencyQueue);

            while (!copy.isEmpty()) {
                sb.append(copy.poll()).append("\n");
            }

            return sb.toString();
        }
    }


    // method to save records calls filemanager methods and saves info from patients
    public void saveRecords() {
        FileManager.savePatients(patients);
    }
}
