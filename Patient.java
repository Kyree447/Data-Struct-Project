package org.example;

public class Patient {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String condition;
    private String hospital;
    private String admissionType;
    private double billingAmount;
    private String severity;

    public Patient(int id,
                   String name,
                   int age,
                   String gender,
                   String condition,
                   String hospital,
                   String admissionType,
                   double billingAmount,
                   String severity) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.condition = condition;
        this.hospital = hospital;
        this.admissionType = admissionType;
        this.billingAmount = billingAmount;
        this.severity = severity;
}

public int getId() {
    return id;
}

public String getCondition() {
    return condition;
}

public String getName() {
        return name;
    }

    public int getSeverityLevel() {
        switch (severity.toUpperCase()) {
            case "RED": return 0;
            case "ORANGE": return 1;
            case "YELLOW": return 2;
            case "GREEN": return 3;
            default: return 4; // unknown severity
        }
    }
    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Age: " + age +
                " | Gender: " + gender +
                " | Condition: " + condition +
                " | Severity: " + severity +
                " | Hospital: " + hospital +
                " | Admission: " + admissionType +
                " | Billing: $" + billingAmount;
    }
    public static Patient fromString(String line) {
        String[] parts = line.split("\\|");

        int id = Integer.parseInt(parts[0].split(":")[1].trim());
        String name = parts[1].split(":")[1].trim();
        int age = Integer.parseInt(parts[2].split(":")[1].trim());
        String gender = parts[3].split(":")[1].trim();
        String condition = parts[4].split(":")[1].trim();
        String severity = parts[5].split(":")[1].trim();
        String hospital = parts[6].split(":")[1].trim();
        String admission = parts[7].split(":")[1].trim();

        // Remove $ and spaces before parsing
        String billingRaw = parts[8].split(":")[1].trim().replace("$", "");
        double billing = Double.parseDouble(billingRaw);

        return new Patient(id, name, age, gender, condition,
                hospital, admission, billing, severity);
    }





}
