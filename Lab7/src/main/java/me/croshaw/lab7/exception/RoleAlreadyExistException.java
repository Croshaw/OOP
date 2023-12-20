package me.croshaw.lab7.exception;

public class RoleAlreadyExistException extends Exception {
    public RoleAlreadyExistException() {
        super("Employee role already exists!");
    }
}
