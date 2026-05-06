package org.example;

public class DoctorInfo {
    private int doctorID;
    private String doctorName;
    private String[] availability;
    private String specialization;

    public DoctorInfo(int doctorID, String doctorName, String[] availability, String specialization) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.availability = availability;
        this.specialization = specialization;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String[] getAvailability() {
        return availability;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void updateAvailability(String[] newAvailability) {
        this.availability = newAvailability;
    }

    public String displayInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("Doctor ID: ").append(doctorID).append("\n");
        sb.append("Name: ").append(doctorName).append("\n");
        sb.append("Specialization: ").append(specialization).append("\n");
        sb.append("Availability: ");

        for (String time : availability) {
            sb.append(time).append(" ");
        }

        return sb.toString();
    }
}
