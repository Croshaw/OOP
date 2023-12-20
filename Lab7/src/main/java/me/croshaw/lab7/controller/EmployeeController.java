package me.croshaw.lab7.controller;

import me.croshaw.lab7.entity.Employee;
import me.croshaw.lab7.exception.EmployeeAlreadyExistException;
import me.croshaw.lab7.exception.EmployeeNotFoundException;
import me.croshaw.lab7.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public ResponseEntity addEmployee(@RequestBody Employee employee) {
        try {
            employeeService.addEmployee(employee);
            return ResponseEntity.ok("Success");
        }
        catch (EmployeeAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getEmployee(@PathVariable("id")  Integer id) {
        try {
            return ResponseEntity.ok(employeeService.findEmployeeByID(id));
        }
        catch (EmployeeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @GetMapping("/")
    public ResponseEntity getEmployees() {
        try {
            return ResponseEntity.ok(employeeService.getEmployees());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@RequestBody Employee employee, @PathVariable("id") Integer employeeId) {
        try {
            employeeService.updateEmployee(employee, employeeId);
            return ResponseEntity.ok("Success");
        }
        catch (EmployeeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Integer id) {
        try {
            employeeService.removeEmployeeById(id);
            return ResponseEntity.ok("Success");
        }
        catch (EmployeeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
