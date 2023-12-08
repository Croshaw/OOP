package db;

import model.Employee;
import model.EmployeeRole;
import table.EmployeeRolesTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesApi {

    private static int getIdLastRow() throws SQLException, ClassNotFoundException {
        var temp = DataBaseApi.getTable("employees");
        return Integer.parseInt(temp.get(temp.size()-1).get(0));
    }
    public static Employee addEmployee(String surname, String name, String patronymic, int roleId) {
        try {
            DataBaseApi.executeQuery("INSERT INTO employees (surname, name, patronymic, role_id) values('%s', '%s', '%s', %s)".formatted(surname, name, patronymic, roleId));
            int id = getIdLastRow();
            return new Employee(id, surname, name, patronymic, EmployeeRolesTable.getRoleById(roleId));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static boolean deleteEmployee(int id) {
        try {
            DataBaseApi.executeQuery("DELETE FROM employees WHERE id='%s'".formatted(id));
            return true;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static boolean editEmployee(int id, String colName, String value) {
        try {
            DataBaseApi.executeQuery("UPDATE employees SET %s='%s' WHERE id=%s".formatted(colName, value, id));
            return true;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<Employee> getTable() {
        try {
            ArrayList<Employee> roles = new ArrayList<>();
            ArrayList<ArrayList<String>> temp = DataBaseApi.getTable("employees");
            temp.forEach(x-> roles.add(new Employee(Integer.parseInt(x.get(0)), x.get(1), x.get(2), x.get(3), EmployeeRolesTable.getRoleById(Integer.parseInt(x.get(4))))));
            return roles;

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
