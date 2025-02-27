package com.cybersploit.streamapi.Service;

import com.cybersploit.streamapi.Entity.Employee;
import com.cybersploit.streamapi.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    //API 1: Retrieve a list of all unique first names of employees.
    public List<String> getAllUniqueFirstName(){
       return employeeRepository.findAll().stream()
                .map(Employee::getFname)
                .distinct()
                .collect(Collectors.toList());
    }
   //API 2: Count the total number of employees in the database.
    public Long getEmployeeCount(){
        return employeeRepository.findAll().stream().count();
    }
    //API 3: Filter employees with the first name starting with the letter "S"
    public List<Employee> getEmployeeWithFistNameStartingWithS(){
        String prefix="s";
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getFname().toLowerCase().startsWith(prefix.toLowerCase()))
                 .toList();
    }

    //API 4: Get a list of employees who have attended more than 75% of classes
    public List<Employee> getEmployeeWithHighAttendance(){
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getAttendance()>75)
                .toList();
    }

    // API 5: Create a list of employees who are in the “IT” department
    public List<Employee> getEmployeeInITDepartment(){
      return  employeeRepository.findAll().stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase("it"))
                .toList();
    }
   // API 6: Count how many employees belong to each department
    public Map<String,Long> getEmployeeCountByDept(){
        return employeeRepository.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
    }
    // API 7: Retrieve the last names of employees with a specific email domain
    public List<String> getLastNameByEmailDomain(){
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getEmail().endsWith(".in"))
                .map(Employee::getLname)
                .toList();
    }
    // API 8 : Find the employee with the highest attendance
    public Optional<Employee> getEmployeeByHighestAttendance(){
       return employeeRepository.findAll().stream()
                .max(Comparator.comparing(Employee::getAttendance));
    }

    // API 9 : Get the average attendance percentage of employees in each department
    public Map<String,Double> getAverageAttendanceByDepartment(){
        return employeeRepository.findAll().stream()
                .collect(Collectors.groupingBy(e->e.getDepartment(),Collectors.averagingDouble(e->e.getAttendance())));
    }
    // API 10 : Sort employees by last name in ascending order.
    public List<Employee> getEmployeeSortedByLastName(){
        return employeeRepository.findAll().stream()
                .sorted(Comparator.comparing(employee -> employee.getLname().toUpperCase()))
                .toList();
    }
    // API 11: Group employees by their subjects and count the number of employees per subject
    public Map<String,Long> getEmployeeCountBySubject(){
        return employeeRepository.findAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getSubject().toUpperCase(),Collectors.counting()));
    }
    // API 12: Find all employees whose roll number is greater than 120
    public List<Employee> getEmployeeWithRollGreaterThan120(){
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getRollNumber()>120)
                .toList();
    }

    // API 13: Get employees who are in  "Java" or "Python".
    public List<Employee> getEmployeesFromJavaAndPython(){
      return  employeeRepository.findAll().stream()
                .filter(e->e.getSubject().equalsIgnoreCase("java")|| e.getSubject().equalsIgnoreCase("Python"))
                .toList();
    }
    // API 14: Count how many employees have an even roll number
    public Long getEmployeeCountByEvenRollNumber(){
       return employeeRepository.findAll().stream()
                .filter(e->e.getRollNumber()%2==0)
                .count();


    }

}

