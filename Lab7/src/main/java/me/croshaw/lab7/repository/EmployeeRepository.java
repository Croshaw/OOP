package me.croshaw.lab7.repository;

import me.croshaw.lab7.entity.Employee;
import org.springframework.data.repository.CrudRepository;
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Employee findEmployeeByid(Integer id);
}
