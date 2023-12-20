package me.croshaw.lab7.service;

import me.croshaw.lab7.entity.Employee;
import me.croshaw.lab7.exception.EmployeeAlreadyExistException;
import me.croshaw.lab7.exception.EmployeeNotFoundException;
import me.croshaw.lab7.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> result = new ArrayList<>();
        employeeRepository.findAll().forEach(result::add);
        return result;
    }
    public Employee findEmployeeByID(Integer id) throws EmployeeNotFoundException {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent())
            return opt.get();
        else
            throw new EmployeeNotFoundException();
    }
    public void addEmployee(Employee employee) throws EmployeeAlreadyExistException {
        if(employeeRepository.findEmployeeByid(employee.getId()) != null) {
            throw new EmployeeAlreadyExistException();
        }
        employeeRepository.save(employee);
    }
    public void updateEmployee(Employee updEmployee, Integer id) throws EmployeeNotFoundException {
        var employee = employeeRepository.findEmployeeByid(id);
        if(employee == null)
            throw new EmployeeNotFoundException();
        if(Objects.nonNull(updEmployee.getSurname()))
            employee.setSurname(updEmployee.getSurname());
        if(Objects.nonNull(updEmployee.getName()))
            employee.setName(updEmployee.getName());
        if(Objects.nonNull(updEmployee.getPatronymic()))
            employee.setPatronymic(updEmployee.getPatronymic());
        if(Objects.nonNull(updEmployee.getEmployeeRole()))
            employee.setEmployeeRole(updEmployee.getEmployeeRole());
        employeeRepository.save(employee);
    }
    public void removeEmployeeById(Integer id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findEmployeeByid(id);
        if(employee == null) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.delete(employee);
    }
    public void removeEmployee(Employee employee) throws EmployeeNotFoundException {
        if(employeeRepository.findEmployeeByid(employee.getId()) == null) {
            throw new EmployeeNotFoundException();
        }
        employeeRepository.delete(employee);
    }
    public void deleteAllData() {
        employeeRepository.deleteAll();
    }
}
