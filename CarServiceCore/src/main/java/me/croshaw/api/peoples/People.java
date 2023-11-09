package me.croshaw.api.peoples;

import me.croshaw.api.Permission;
import me.croshaw.api.Role;

import java.time.LocalDate;

public abstract class People {
    protected long id;
    protected String surname;
    protected String name;
    protected String patronymic;
    protected String phoneNumber;
    protected LocalDate dateOfBirth;
    protected String username;
    protected String password;
    protected Role role;
    public People(long id, String surname, String name, String patronymic, String phoneNumber, LocalDate dateOfBirth, String username, String password, Role role) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public long getId() {
        return id;
    }
    public String getSurname() {
        return surname;
    }
    public String getName() {
        return name;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }
    public boolean hasPermission(Permission permission) {
        return role.hasPermission(permission);
    }
    public int getAge() {
        return LocalDate.now().getYear() - getDateOfBirth().getYear();
    }

    @Override
    public String toString() {
        return "Фамилия: %s Имя: %s Отчество: %s Год рождения: %s".formatted(getSurname(), getName(), getPatronymic(), getDateOfBirth());
    }

    public String toShortString() {
        return "%s %c. %c.".formatted(getSurname(), Character.toUpperCase(getName().charAt(0)), Character.toUpperCase(getPatronymic().charAt(0)));
    }
}