package org.example;

import java.util.*;

public class HospitalManager {

    private LinkedList<Patient> patients = new LinkedList<>();
    private Stack<Patient> deletedPatients = new Stack<>();
    private HashMap<Integer, Patient> patientDirectory = new HashMap<>();
    private PriorityQueue<Patient> emergencyQueue =
            new PriorityQueue<>((p1, p2) ->
                    p1.getSeverityLevel() - p2.getSeverityLevel());

    // CONSTRUCTOR
    public HospitalManager() {
        patients = FileManager.loadPatients();

        // Rebuild HashMap + PriorityQueue
        for (Patient p : patients) {
            patientDirectory.put(p.getId(), p);

            if (p.getSeverityLevel() <= 1) {
                emergencyQueue.add(p);
            }
        }
    }

    // ADD PATIENT
    public void addPatient(Patient patient) {
        patients.add(patient);

        if (patient.getSeverityLevel() <= 1) {
            emergencyQueue.add(patient);
        }

        patientDirectory.put(patient.getId(), patient);
    }

    // DELETE PATIENT
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

    // UNDO DELETE
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

    // LOOKUP BY ID
    public String lookupPatient(int id) {
        Patient p = patientDirectory.get(id);
        return (p != null) ? p.toString() : "No patient found with that ID.";
    }

    // VIEW ALL PATIENTS
    public String viewAllPatients() {
        if (patients.isEmpty()) return "No patients available.";

        StringBuilder sb = new StringBuilder();
        for (Patient p : patients) sb.append(p).append("\n");
        return sb.toString();
    }

    // SEARCH BY CONDITION
    public String searchCondition(String condition) {
        StringBuilder sb = new StringBuilder();

        for (Patient p : patients) {
            if (p.getCondition().equalsIgnoreCase(condition)) {
                sb.append(p).append("\n");
            }
        }

        return sb.length() == 0 ? "No patients found." : sb.toString();
    }

    // VIEW EMERGENCY PATIENTS
    public String viewEmergencyPatients() {
        if (emergencyQueue.isEmpty()) return "No emergency patients.";

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Patient> copy = new PriorityQueue<>(emergencyQueue);

        while (!copy.isEmpty()) {
            sb.append(copy.poll()).append("\n");
        }

        return sb.toString();
    }

    // SAVE RECORDS
    public void saveRecords() {
        FileManager.savePatients(patients);
    }
}
