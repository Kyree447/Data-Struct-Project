import java.util.ArrayList;
import java.util.Scanner;

public class HospitalRecords {
    public static void main(String[] args) {

        ArrayList<PatientRecord> recordsList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many patient records do you want to enter? ");
        int numRecords = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        for (int i = 0; i < numRecords; i++) {
            System.out.println("\nEnter details for patient " + (i + 1));

            System.out.print("Enter ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Gender: ");
            String gender = scanner.nextLine();

            System.out.print("Enter Medical Condition: ");
            String medicalCondition = scanner.nextLine();

            System.out.print("Enter Hospital: ");
            String hospital = scanner.nextLine();

            System.out.print("Enter Admission Type: ");
            String admissionType = scanner.nextLine();

            System.out.print("Enter Billing Amount: ");
            double billingAmount = scanner.nextDouble();
            scanner.nextLine();

            PatientRecord record = new PatientRecord(
                    id, age, gender, medicalCondition,
                    hospital, admissionType, billingAmount
            );

            recordsList.add(record);
        }

        System.out.println("\nPatient Records:");
        for (PatientRecord record : recordsList) {
            System.out.println(record);
        }

        scanner.close();
    }
}
