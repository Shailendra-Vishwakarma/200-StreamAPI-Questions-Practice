package com.cybersploit.streamapi.Controller;

import com.cybersploit.streamapi.Entity.Employee;
import com.cybersploit.streamapi.Service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Tag(name = "Employee API", description = "Employee APIs Created using Stream API - Java 8 New Feature.")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all-unique-fname")
    public ResponseEntity<List<String>> getAllUniqueFname(){
        return ResponseEntity.ok(employeeService.getAllUniqueFirstName());
    }
    @GetMapping("/total-count")
    public ResponseEntity<Long> getEmployeeCount(){
        return ResponseEntity.ok(employeeService.getEmployeeCount());
    }

    @GetMapping("/first-name-start-with-S")
    public ResponseEntity<List<Employee>> getEmployeeWithFistNameStartingWithS(){
        return ResponseEntity.ok(employeeService.getEmployeeWithFistNameStartingWithS());
    }

    @GetMapping("/high-attendance")
    public ResponseEntity<List<Employee>> getEmployeeWithHighAttendance(){
        return ResponseEntity.ok(employeeService.getEmployeeWithHighAttendance());
    }

    @GetMapping("/it-department-emp")
    public ResponseEntity<List<Employee>> getEmployeeInITDepartment(){
        return ResponseEntity.ok(employeeService.getEmployeeInITDepartment());
    }

    @GetMapping("/count-by-department")
    public ResponseEntity<Map<String,Long>> getEmployeeCountByDept(){
        return ResponseEntity.ok(employeeService.getEmployeeCountByDept());
    }

    @GetMapping("/last-name-by-email-domain")
    public ResponseEntity<List<String>> getLastNameByEmailDomain(){
        return ResponseEntity.ok(employeeService.getLastNameByEmailDomain());
    }

    @GetMapping("/highest-attendance")
    public ResponseEntity<Optional<Employee>> getEmployeeByHighestAttendance(){
        return ResponseEntity.ok(employeeService.getEmployeeByHighestAttendance());
    }

    @GetMapping("/average-attendance-by-department")
    public ResponseEntity<Map<String,Double>> getAverageAttendanceByDepartment(){
        return ResponseEntity.ok(employeeService.getAverageAttendanceByDepartment());
    }

    @GetMapping("/sort-by-last-name")
    public ResponseEntity<List<Employee>> getEmployeeSortedByLastName(){
        return ResponseEntity.ok(employeeService.getEmployeeSortedByLastName());
    }
    @GetMapping("/count-emp-by-each-subject")
    public ResponseEntity<Map<String,Long>> getEmployeeCountBySubject(){
        return ResponseEntity.ok(employeeService.getEmployeeCountBySubject());
    }
    @GetMapping("/roll-greaterThan-120")
    public ResponseEntity<List<Employee>> getEmployeeWithRollGreaterThan120(){
        return ResponseEntity.ok(employeeService.getEmployeeWithRollGreaterThan120());
    }

    @GetMapping("Emp-from-java-and-python")
    public ResponseEntity<List<Employee>> getEmployeesFromJavaAndPython(){
        return ResponseEntity.ok(employeeService.getEmployeesFromJavaAndPython());
    }

}

