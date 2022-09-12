package PatientPackage;

public class Patient {
    private int id;
    private String firstName, surname, patronymic;
    private String address;
    private int telNumber;
    private int medicalCardNumber;
    private String diagnosis;

    /**
     *Конструктор з усіма параметрами
     * @param id айді
     * @param firstName ім'я
     * @param surname прізвище
     * @param patronymic по-батькові
     * @param address адреса
     * @param medicalCardNumber номер медичної картки
     * @param diagnosis діагноз
     */

    public Patient(int id, String firstName, String surname, String patronymic, String address, int telNumber, int medicalCardNumber, String diagnosis) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = address;
        this.telNumber=telNumber;
        this.medicalCardNumber = medicalCardNumber;
        this.diagnosis = diagnosis;
    }


    //сетери і гетери
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(int telNumber) {
        this.telNumber = telNumber;
    }

    public int getMedicalCardNumber() {
        return medicalCardNumber;
    }

    public void setMedicalRecordNumber(int medicalCardNumber) {
        this.medicalCardNumber = medicalCardNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Перевизначений метод toString
     * @return поля об'єкту класу і їх значення у вигляді строки
     */
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", address='" + address + '\'' +
                ", telephoneNumber=" + telNumber +
                ", medicalRecordNumber=" + medicalCardNumber +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }

}
