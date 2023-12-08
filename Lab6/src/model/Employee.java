package model;

public class Employee extends Model {
    private final int id;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final EmployeeRole role;

    public Employee(int id, String surname, String name, String patronymic, EmployeeRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public EmployeeRole getRole() {
        return role;
    }
}
