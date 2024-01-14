package com.myHR.api_sb.controller;
import com.myHR.api_sb.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.myHR.api_sb.model.Employee;
import com.myHR.api_sb.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;



import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/")
public class EmployeeController{
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Read - Get all employees
     * @return - An Iterable object of Employee full filled
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }


    @GetMapping("/detailEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            System.out.println("employee deleted");
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/addEmployeeForm")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("newEmployee", new Employee());
        return "addEmployeeForm";
    }


        @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) {
        Employee savedEmployee = employeeService.saveEmployee(newEmployee);
        if (savedEmployee != null) {
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee updated = employeeService.updateEmployee(id, updatedEmployee);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* hta hadi khdama
    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("newEmployee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }
*/
    /* hta hadi khdama bla service
   @PutMapping("/modify/{id}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable Long id, @RequestBody Employee EmployeeUpdated){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            Employee ExistentEm = optionalEmployee.get();
            ExistentEm.setFirstName(EmployeeUpdated.getFirstName());
            ExistentEm.setLastName(EmployeeUpdated.getLastName());
            ExistentEm.setMail(EmployeeUpdated.getMail());
            ExistentEm.setPassword(EmployeeUpdated.getPassword());
            employeeRepository.save(ExistentEm);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
*/




}
