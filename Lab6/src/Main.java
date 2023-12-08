import db.EmployeeRolesApi;
import model.Employee;
import table.EmployeeRolesTable;
import table.EmployeesTable;

import java.io.IOException;
import java.util.*;

public class Main {
        public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("[1] - Таблица [employee_roles]\n[2] - Таблица [employees]\n\n[0] - Завершить программу");
            String res = in.next();
            if(res.equals("0"))
                break;
            if(res.equals("1") || res.equals("2")) {
                while(true) {
                    System.out.println("[1] - Добавить запись\n[2] - Удалить запись\n[3] - Редактировать запись\n[4] - Вывести таблицу\n\n[0] - Назад");
                    String res2 = in.next();
                    if(res2.equals("0"))
                        break;
                    switch (res2) {
                        case("1"):
                            if(res.equals("1")) {
                                System.out.print("Введите название роли: ");
                                res2 = in.next();
                                EmployeeRolesTable.addRole(res2);
                            } else {
                                System.out.print("Введите фамилию, имя, отчество и id роли(через пробел):\n");
                                res2 = "";
                                for(int i =0;i<4;i++)
                                    res2 += in.next() + " ";
                                String[] temp = res2.split(" ");
                                if(temp.length != 4)
                                    break;
                                EmployeesTable.add(temp[0], temp[1], temp[2], Integer.parseInt(temp[3]));
                            }
                            break;
                        case("2"):
                            if(res.equals("1")) {
                                System.out.print("Введите название роли: ");
                                res2 = in.next();
                                EmployeeRolesTable.removeRole(res2);
                            } else {
                                System.out.print("Введите id сотрудника: ");
                                int id = in.nextInt();
                                EmployeesTable.remove(id);
                            }
                            break;
                        case("3"):
                            if(res.equals("1")) {
                                System.out.print("Введите id роли: ");
                                int id = in.nextInt();
                                System.out.print("Введите новое название роли: ");
                                res2 = in.next();
                                EmployeeRolesTable.editRole(id, res2);
                            } else {
                                System.out.print("Введите id сотрудника: ");
                                int id = in.nextInt();
                                Employee employee = EmployeesTable.getEmployeeById(id);
                                if(employee == null)
                                {
                                    System.err.println("Сотрудника с таким id не существует!");
                                    break;
                                }
                                System.out.println(employee);
                                Map<String, String> map = Map.of(
                                        "surname", "Фамилия",
                                        "name", "Имя",
                                        "patronymic", "Отчёство",
                                        "roleId", "Роль"
                                );
                                int tI = 1;
                                Set<String> tS = map.keySet();
                                for(String str : tS) {
                                    System.out.printf("[%s] - %s\n", tI++, map.get(str));
                                }
                                System.out.print("Что хотите изменить: ");
                                int tempN = in.nextInt();
                                if(tempN < 1 || tempN >= tS.size())
                                    break;
                                System.out.print("Введите новое значение: ");
                                String tempS = in.next();
                                tI = 1;
                                for(String str : tS)
                                    if(tI++ == tempN) {
                                        EmployeesTable.edit(id, str, tempS);
                                        break;
                                    }
                            }
                            break;
                        case("4"):
                            if(res.equals("1")) {
                                System.out.println(EmployeeRolesTable.toStr());
                            } else {
                                System.out.println(EmployeesTable.toStr());
                            }
                            break;
                    }
                }
            }
        }
    }
}