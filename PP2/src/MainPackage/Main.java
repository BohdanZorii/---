package MainPackage;

import ManagmentPackage.PatientsManager;
import PatientPackage.Patient;

public class Main {

    /**
     * Тестуємо нашу програму
     * @param args
     */
    public static void main(String[] args) {

        PatientsManager manager = new PatientsManager();
        System.out.println(manager);


        manager.filterPatientsByCertainCard(10,50);
        System.out.println(manager);

        manager.filterPatientsByDiagnosis("Leukemia");
        System.out.println(manager);

        manager.filterPatientsByTelNumber(2);
        System.out.println(manager);
    }




}

