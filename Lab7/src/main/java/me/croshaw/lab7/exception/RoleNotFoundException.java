package me.croshaw.lab7.exception;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException() {
        super("Employee role not found");
    }
}
