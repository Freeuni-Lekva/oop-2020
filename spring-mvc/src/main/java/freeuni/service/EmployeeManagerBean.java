package freeuni.service;

import freeuni.model.Employee;
import freeuni.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManagerBean implements EmployeeManager {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeManagerBean(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.getAllEmployees();
    }

    public long addEmployee(String firstName, String lastName) {
        return repository.addEmployee(firstName, lastName);
    }

    public Employee getEmployee(long id) {
        return repository.getEmployee(id);
    }
}
