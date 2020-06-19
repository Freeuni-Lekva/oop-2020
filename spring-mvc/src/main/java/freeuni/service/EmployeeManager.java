package freeuni.service;

import freeuni.model.Employee;

import java.util.List;

public interface EmployeeManager {

    List<Employee> getAllEmployees();

    long addEmployee(String firstName, String lastName);

    public Employee getEmployee(long id);

}
