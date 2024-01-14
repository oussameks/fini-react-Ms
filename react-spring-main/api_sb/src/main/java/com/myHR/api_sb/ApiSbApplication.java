package com.myHR.api_sb;

import com.myHR.api_sb.model.Employee;
import com.myHR.api_sb.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSbApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
		return args -> {


			Employee employee1 = Employee.builder().firstName("oussama").lastName("mssk").mail("ok@ok.ok").password("azertyik").build();
			Employee employee2 = Employee.builder().firstName("hachim").lastName("razi").mail("ppms@plsm.fr").password("madrasa212").build();
			Employee employee3 = Employee.builder().firstName("youssra").lastName("jiha").mail("jij@mql.vb").password("youssra66").build();

			employeeRepository.save(employee1);
			employeeRepository.save(employee2);
			employeeRepository.save(employee3);


		};
	}

}
