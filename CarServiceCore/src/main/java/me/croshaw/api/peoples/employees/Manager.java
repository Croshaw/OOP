package me.croshaw.api.peoples.employees;

import me.croshaw.api.Role;
import me.croshaw.api.peoples.People;

import java.time.LocalDate;

public class Manager extends People {
    public Manager(long id, String surname, String name, String patronymic, String phoneNumber, LocalDate dateOfBirth, String username, String password) {
        super(id, surname, name, patronymic, phoneNumber, dateOfBirth, username, password, Role.Manager);
    }
}
