package db;

import model.EmployeeRole;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeRolesApi {
    private static int getIdLastRow() throws SQLException, ClassNotFoundException {
        var temp = DataBaseApi.getTable("employee_roles");
        return Integer.parseInt(temp.get(temp.size()-1).get(0));
    }
    public static String getName(int id) throws SQLException, ClassNotFoundException {
        return DataBaseApi.getData("employee_roles", "name", "id", Integer.toString(id));
    }
    public static EmployeeRole addRole(String name) {
        try {
            DataBaseApi.executeQuery("INSERT INTO employee_roles (name) values('%s')".formatted(name));
            int id = getIdLastRow();
            return new EmployeeRole(id, name);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static boolean deleteRole(String name) {
        try {
            DataBaseApi.executeQuery("DELETE FROM employee_roles WHERE name='%s'".formatted(name));
            return true;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static boolean editRole(int id, String newName) {
        try {
            DataBaseApi.executeQuery("UPDATE employee_roles SET name='%s' WHERE id=%s".formatted(newName, id));
            return true;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<EmployeeRole> getTable() {
        try {
            ArrayList<EmployeeRole> roles = new ArrayList<>();
            ArrayList<ArrayList<String>> temp = DataBaseApi.getTable("employee_roles");
            temp.forEach(x-> roles.add(new EmployeeRole(Integer.parseInt(x.get(0)), x.get(1))));
            return roles;

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
