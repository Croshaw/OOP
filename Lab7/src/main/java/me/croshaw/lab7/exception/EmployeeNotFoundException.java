package me.croshaw.lab7.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException() {
        super("Employee not found");
    }
}
