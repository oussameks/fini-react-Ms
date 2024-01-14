package com.employees.webapp.repository;

import com.employees.webapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface Emprepo extends JpaRepository<Employee, Long> {
}
