package model;

import table.EmployeeRolesTable;

public class EmployeeRole extends Model {
    private final int id;
    private final String name;

    public EmployeeRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    @Override
//    public String toString() {
////        return "EmployeeRole{" +
////                "id=" + id +
////                ", name='" + name + '\'' +
////                '}';
//        return "%s %s".formatted(id, name);
//    }
}
