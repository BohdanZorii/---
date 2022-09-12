package ManagmentPackage;

import PatientPackage.Patient;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * клас-менеджер для списку пацієнтів
 */
public class PatientsManager {

    private Patient[] patients;

    /**
     * конструктор
     */
    public PatientsManager(){
        patients= readPatientsFromKeyboard();
    }

    /**
     * метод, що зчитує дані з клавіатури і формує список пацієнтів
     * @return список пацієнтів
     * @throws InputMismatchException при помилці введення
     */
    private static Patient[] readPatientsFromKeyboard() throws InputMismatchException{

        String firstName, surname, patronymic, address, diagnosis;
        int id, telNumber, medicalCardNumber;

        Scanner scanner = new Scanner(System.in);


            System.out.print("Enter number of patients: ");
            Patient[] patients = new Patient[scanner.nextInt()];     scanner.nextLine();
        try {
            for (int i=0; i<patients.length; i++) {
                System.out.println("\nData input about the next patient: \n");

                System.out.print("Enter patient's first name : ");
                firstName=scanner.nextLine();
                System.out.print("Enter patient's surname : ");
                surname=scanner.nextLine();
                System.out.print("Enter patient's patronymic : ");
                patronymic=scanner.nextLine();
                System.out.print("Enter patient's address : ");
                address=scanner.nextLine();
                System.out.print("Enter patient's diagnosis : ");
                diagnosis=scanner.nextLine();

                System.out.print("Enter patient's id : ");
                id=scanner.nextInt();  scanner.nextLine();
                System.out.print("Enter patient's telephone number : ");
                telNumber=scanner.nextInt();  scanner.nextLine();
                System.out.print("Enter patient's medical card number : ");
                medicalCardNumber=scanner.nextInt();  scanner.nextLine();

                patients[i]=new Patient(id, firstName,surname,patronymic,address,telNumber,medicalCardNumber,diagnosis);
            }

        }catch(InputMismatchException e){
            throw new InputMismatchException("You have to enter integer values for id, telephone and medical card numbers!");
        }
        return patients;
    }

    /**
     * заміняє старий список відфільтрованим, якщо є зміни і відфільтрований не порожній
     * @param newPatientsList відфільтрований список
     */
    private void replacePatientsListIfChanged(Patient[] newPatientsList){
        System.out.println("Trying to replace old list with a filtered one...");

        if(newPatientsList==null || newPatientsList.length==0){
            System.out.println("Filtered list is empty :(");
            return;
        }

        if(newPatientsList.length!=patients.length){
            patients=newPatientsList;
            System.out.println("Success!");
        }else{
            System.out.println("Nothing was changed!");
        }

    }

    /**
     * фільтрує за діагнозом
     * @param diagnosis
     */
    public void filterPatientsByDiagnosis(String diagnosis) {
        System.out.println("\nFiltering list by diagnosis: "+diagnosis);

        Patient[] patientsWithThisDiagnosis = new Patient[patients.length];
        int i = 0;
        for (Patient patient : patients) {
            if (patient.getDiagnosis().equalsIgnoreCase(diagnosis))
                patientsWithThisDiagnosis[i++] = patient;
        }
        replacePatientsListIfChanged(Arrays.copyOf(patientsWithThisDiagnosis, i));
    }

    /**
     * фільтрує за номером картки
     * @param min мінімальний номер картки
     * @param max максимальний номер картки
     */
    public void filterPatientsByCertainCard(int min, int max){
        if(min>max)
            throw new IllegalArgumentException("Min range is greater than max range!");

        System.out.println("\nFiltering list by medical card number range: ["+min+"; "+max+"]");

        Patient[] patientsWithCertainCard = new Patient[patients.length];
        int i = 0;
        for (Patient patient : patients) {
            if ((patient.getMedicalCardNumber()>min) && (patient.getMedicalCardNumber()<max))
                patientsWithCertainCard[i++] = patient;
        }
        replacePatientsListIfChanged(Arrays.copyOf(patientsWithCertainCard, i));
    }

    /**
     * фільрує за першою цифрою телефонного номеру
     * @param startDigit перша цифра
     */
    public void filterPatientsByTelNumber(int startDigit){
        if((startDigit<0) || (startDigit>9))
            throw new IllegalArgumentException("Argument value is not a digit!");

        System.out.println("\nFiltering list by first digit of telephone number: "+startDigit);

        Patient[] patientsWithCertainNumber = new Patient[patients.length];
        int i = 0;
        for (Patient patient : patients) {
            int telNumber = patient.getTelNumber();
            while (telNumber>9) telNumber/=10;
            if (telNumber == startDigit)
                patientsWithCertainNumber[i++] = patient;
        }
        replacePatientsListIfChanged(Arrays.copyOf(patientsWithCertainNumber, i));
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("\nList of "+patients.length+" patients:\n");
        for (Patient patient: patients) {
            builder.append(patient).append("\n");
        }
        return builder.toString();
    }

}
