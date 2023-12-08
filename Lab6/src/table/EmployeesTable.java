package table;

import db.EmployeesApi;
import model.Employee;
import model.EmployeeRole;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Queue;
import java.util.stream.Collectors;

public class EmployeesTable {
    private static final ArrayList<Employee> employees = EmployeesApi.getTable();
    public static Employee getEmployeeById(int id) {
        assert employees != null;
        for (Employee employee : employees) {
            if(employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
    public static void add(String surname, String name, String patronymic, int roleId) {
        Employee employee = EmployeesApi.addEmployee(surname, name, patronymic, roleId);
        if(employee!=null)
            employees.add(employee);
    }

    public static void remove(int id) {
        Employee employee = getEmployeeById(id);
        if(employee != null)
            remove(employee);
    }
    public static void remove(Employee role) {
        if(EmployeesApi.deleteEmployee(role.getId()))
            employees.remove(role);
    }
    public static void removeByRole(EmployeeRole role) {
        ArrayList<Employee> toRemove = new ArrayList<>();
        for(Employee employee : employees)
            if(employee.getRole() == role)
                toRemove.add(employee);
        toRemove.forEach(employees::remove);
        toRemove.clear();
    }
    public static void edit(int id, String colName, String newValue) {
        Employee employee = getEmployeeById(id);
        if(employee != null)
            editRole(employee, colName, newValue);
    }
    public static void editRole(Employee employee, String colName, String newValue) {
        if(EmployeesApi.editEmployee(employee.getId(), colName, newValue)) {
            assert employees != null;
            employees.remove(employee);
            String name = employee.getName();
            String surname = employee.getSurname();
            String patronymic = employee.getPatronymic();
            EmployeeRole employeeRole = employee.getRole();
            switch (colName) {
                case "surname" -> surname = newValue;
                case "name" -> name = newValue;
                case "patronymic" -> patronymic = newValue;
                case "roleId" -> employeeRole = EmployeeRolesTable.getRoleById(Integer.parseInt(newValue));
            }
            employees.add(new Employee(employee.getId(), surname, name, patronymic, employeeRole));
        }
    }
    public static String toStr() {
        return "[employees]\nid name surname patronymic role\n%s".formatted(employees.stream().sorted(Comparator.comparing(Employee::getId)).map(Employee::toString).collect(Collectors.joining("\n")));
    }
}
