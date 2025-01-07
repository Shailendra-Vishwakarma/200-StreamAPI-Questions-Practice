package com.cybersploit.streamapi.Service;

import com.cybersploit.streamapi.Entity.Employee;
import com.cybersploit.streamapi.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Integer id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setFname(updatedEmployee.getFname());
                    employee.setLname(updatedEmployee.getLname());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setRollNumber(updatedEmployee.getRollNumber());
                    employee.setSubject(updatedEmployee.getSubject());
                    employee.setClassesAttended(updatedEmployee.getClassesAttended());
                    employee.setAttendance(updatedEmployee.getAttendance());
                    return employeeRepository.save(employee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
    //Q1: Retrieve a list of all unique first names of employees.
    public List<String> getAllUniqueFirstName(){
       return employeeRepository.findAll().stream()
                .map(Employee::getFname)
                .distinct()
                .collect(Collectors.toList());
    }
   //Q2: Count the total number of employees in the database.
    public Long getEmployeeCount(){
        return employeeRepository.findAll().stream().count();
    }


}

