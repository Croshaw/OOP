package table;

import db.EmployeeRolesApi;
import model.EmployeeRole;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class EmployeeRolesTable {
    private static final ArrayList<EmployeeRole> roles = EmployeeRolesApi.getTable();
    public static EmployeeRole getRoleById(int id) {
        assert roles != null;
        for (EmployeeRole role : roles) {
            if(role.getId() == id) {
                return role;
            }
        }
        return null;
    }
    public static void addRole(String name) {
        EmployeeRole employeeRole = EmployeeRolesApi.addRole(name);
        if(employeeRole!=null)
            roles.add(employeeRole);
    }

    public static void removeRole(String name) {
        for (EmployeeRole role : roles) {
            if(role.getName().equals(name)) {
                removeRole(role);
                return;
            }
        }
    }
    public static void removeRole(EmployeeRole role) {
        if(EmployeeRolesApi.deleteRole(role.getName())) {
            EmployeesTable.removeByRole(role);
            roles.remove(role);
        }

    }
    public static void editRole(int id, String newName) {
        EmployeeRole role = getRoleById(id);
        if(role != null)
            editRole(role, newName);
    }
    public static void editRole(EmployeeRole role, String newName) {
        if(EmployeeRolesApi.editRole(role.getId(), newName)) {
            assert roles != null;
            roles.remove(role);
            roles.add(new EmployeeRole(role.getId(), newName));
        }
    }
    public static String toStr() {
        return "[employee_roles]\nid name\n%s".formatted(roles.stream().sorted(Comparator.comparing(EmployeeRole::getId)).map(EmployeeRole::toString).collect(Collectors.joining("\n")));
    }
}
