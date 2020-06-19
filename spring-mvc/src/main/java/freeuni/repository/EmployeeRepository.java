package freeuni.repository;

import freeuni.DummyDB;
import freeuni.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    public List<Employee> getAllEmployees() {
        return DummyDB.getAllEmployees();
    }

    public long addEmployee(String firstName, String lastName) {
        Employee toAdd = new Employee();
        toAdd.setFirstName(firstName);
        toAdd.setLastName(lastName);
        return DummyDB.addEmployee(toAdd);
    }

    public Employee getEmployee(long id) {
        return DummyDB.getEmployee(id);
    }

}
