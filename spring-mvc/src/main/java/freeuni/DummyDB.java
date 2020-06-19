package freeuni;

import freeuni.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class DummyDB {

    private static List<Employee> employees = new ArrayList<Employee>();

    private static long lastId = 0;

    public static long addEmployee(Employee employee) {
        employee.setId(++lastId);
        employees.add(employee.copy());
        return lastId;
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> result = new ArrayList<Employee>();
        for (Employee employee : employees) {
            result.add(employee.copy());
        }
        return result;
    }

    public static Employee getEmployee(long id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee.copy();
            }
        }
        return null;
    }

}
