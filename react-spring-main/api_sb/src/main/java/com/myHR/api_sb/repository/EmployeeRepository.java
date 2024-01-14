package com.myHR.api_sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.myHR.api_sb.model.Employee;
//@Repository est une annotation Spring pour indiquer que la classe est un bean,
// son rôle est de communiquer avec une source de données ( la base de données par exp).
//@Repository est une spécialisation de @Component.
//@Component, permet de déclarer auprès de Spring qu’une classe est un bean à exploiter.
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {}
