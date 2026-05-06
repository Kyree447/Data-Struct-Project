package org.example;

public class Benchmark {

    public static void run(HospitalManager manager) {

        System.out.println("Benchmark");

        long start, end;

        // benchmark
        start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            Patient p = new Patient(
                    100000 + i,
                    "T" + i,
                    30,
                    "M",
                    "N",
                    "T",
                    "W",
                    100.0,
                    "G"
            );
            manager.addPatient(p);
        }
        end = System.nanoTime();
        System.out.println("Add 10,000 patients: " + (end - start) / 1_000_000.0 + " ms");

        // benchmark lookup
        start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            manager.lookupPatient(100000 + i);
        }
        end = System.nanoTime();
        System.out.println("Lookup 10,000 patients (HashMap): " + (end - start) / 1_000_000.0 + " ms");

        // bnechmark delete
        start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            manager.deletePatient(100000 + i);
        }
        end = System.nanoTime();
        System.out.println("Delete 10,000 patients: " + (end - start) / 1_000_000.0 + " ms");

        // benchmark to save
        start = System.nanoTime();
        manager.saveRecords();
        end = System.nanoTime();
        System.out.println("Save to file: " + (end - start) / 1_000_000.0 + " ms");

        //Benchmark to load from file
        start = System.nanoTime();
        FileManager.loadPatients();
        end = System.nanoTime();
        System.out.println("Load from file: " + (end - start) / 1_000_000.0 + " ms");
    }
}
