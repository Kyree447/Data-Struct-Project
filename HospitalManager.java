package org.example;

import java.util.*;

public class HospitalManager {

    private LinkedList<Patient> patients = new LinkedList<>();
    private Stack<Patient> deletedPatients = new Stack<>();
    private HashMap<Integer, String> patientDirectory = new HashMap<>();
    private PriorityQueue<Patient> emergencyQueue =
            new PriorityQueue<>((p1, p2) ->
                    p1.getSeverityLevel() - p2.getSeverityLevel());

    public void addPatient(Patient patient) {
        patients.add(patient);

        // Add to emergency queue if needed
        if (patient.getSeverityLevel() <= 1) {
            emergencyQueue.add(patient);
        }

        // Add to HashMap
        patientDirectory.put(patient.getId(), patient.getName());
    }

    public void deletePatient(int id) {
        Iterator<Patient> iterator = patients.iterator();

        while (iterator.hasNext()) {
            Patient p = iterator.next();
            if (p.getId() == id) {
                deletedPatients.push(p);
                iterator.remove();
                return;
            }
        }
    }

    public void undoDelete() {
        if (!deletedPatients.isEmpty()) {
            patients.add(deletedPatients.pop());
        }
    }

    public String lookupPatientName(int id) {
        if (patientDirectory.containsKey(id)) {
            return "Patient Name: " + patientDirectory.get(id);
        }
        return "No patient found with that ID.";
    }

    public String viewAllPatients() {
        if (patients.isEmpty()) return "No patients available.";

        StringBuilder sb = new StringBuilder();
        for (Patient p : patients) sb.append(p).append("\n");
        return sb.toString();
    }

    public String searchPatient(int id) {
        for (Patient p : patients) {
            if (p.getId() == id) return p.toString();
        }
        return "Patient not found.";
    }

    public String searchCondition(String condition) {
        StringBuilder sb = new StringBuilder();

        for (Patient p : patients) {
            if (p.getCondition().equalsIgnoreCase(condition)) {
                sb.append(p).append("\n");
            }
        }

        return sb.length() == 0 ? "No patients found." : sb.toString();
    }

    public String viewEmergencyPatients() {
        if (emergencyQueue.isEmpty()) return "No emergency patients.";

        StringBuilder sb = new StringBuilder();
        for (Patient p : emergencyQueue) sb.append(p).append("\n");
        return sb.toString();
    }

    public void saveRecords() {
        FileManager.savePatients(patients);
    }
}
