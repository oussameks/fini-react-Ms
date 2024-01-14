package com.myHR.api_sb.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myHR.api_sb.model.Employee;
import com.myHR.api_sb.repository.EmployeeRepository;
import lombok.Data;
//@Service : tout comme l’annotation @Repository, c’est une spécialisation de @Component.
//Son rôle est le même, mais son nom a une valeur sémantique pour ceux qui lisent le code.
@Data
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(final Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            // Update only non-null fields to avoid overwriting with null values
            if (updatedEmployee.getFirstName() != null) {
                existingEmployee.setFirstName(updatedEmployee.getFirstName());
            }
            if (updatedEmployee.getLastName() != null) {
                existingEmployee.setLastName(updatedEmployee.getLastName());
            }
            if (updatedEmployee.getMail() != null) {
                existingEmployee.setMail(updatedEmployee.getMail());
            }
            if (updatedEmployee.getPassword() != null) {
                existingEmployee.setPassword(updatedEmployee.getPassword());
            }

            // Save the updated employee
            return employeeRepository.save(existingEmployee);
        } else {
            // Handle case where employee with given ID is not found
            return null;
        }
    }


}
