package me.croshaw.lab7.exception;

public class EmployeeAlreadyExistException extends Exception {
    public EmployeeAlreadyExistException() {
        super("Employee already exists!");
    }
}
