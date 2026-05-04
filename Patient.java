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


}
