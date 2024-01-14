package com.employees.webapp.controller;

import com.employees.webapp.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.employees.webapp.repository.Emprepo;
import com.employees.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import com.employees.webapp.model.Employee;
import com.employees.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.employees.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @Autowired
    private Emprepo emprepo;

    @GetMapping("/")
    public String employees(Model model) {
        System.out.println("EmployeeController * employees(Model) ");
        Iterable<Employee> listEmployee = service.getEmployees();
        model.addAttribute("employees", listEmployee);
        return "home";
    }

    @GetMapping("/detailsEmployee/{id}")
    public String employeeDetails(@PathVariable Long id, Model model) {
        System.out.println("EmployeeController * employeeDetails(Model) ");
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employeeDetails";
    }


    @DeleteMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final Long id) {
        service.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/addEmployeeForm")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("newEmployee", new Employee());
        return "addEmployeeForm";
    }

    @PostMapping("/addEmployee")
    public ModelAndView addEmployee(@ModelAttribute("newEmployee") Employee newEmployee) {
        service.addEmployee(newEmployee);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/updateEmployeeForm/{id}")
    public String showUpdateEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "updateEmployeeForm";
    }

    @PostMapping("/updateEmployee")
    public ModelAndView updateEmployee(@ModelAttribute("employee") Employee updatedEmployee) {
        service.updateEmployee(updatedEmployee.getId(), updatedEmployee);
        return new ModelAndView("redirect:/");
    }


}