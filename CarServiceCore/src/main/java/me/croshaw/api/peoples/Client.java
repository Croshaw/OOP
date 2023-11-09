package me.croshaw.api.peoples;

import me.croshaw.api.Role;
import me.croshaw.api.peoples.People;

import java.time.LocalDate;

public class Client extends People {
    public Client(long id, String surname, String name, String patronymic, String phoneNumber, LocalDate dateOfBirth, String username, String password) {
        super(id, surname, name, patronymic, phoneNumber, dateOfBirth, username, password, Role.Client);
    }
}
