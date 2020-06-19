package freeuni.controller;

import freeuni.model.Employee;
import freeuni.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeManager employeeManager;

    @Autowired
    public EmployeeController(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String get(Model model) {
        return "index";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeManager.getAllEmployees());
        return "employees";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getEmployee(Model model, @PathVariable long id) {
        model.addAttribute("employee", employeeManager.getEmployee(id));
        return "employee";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEmployee(Model model, @RequestParam String firstName, @RequestParam String lastName) {
        employeeManager.addEmployee(firstName, lastName);
        return getAllEmployees(model);
    }

}
