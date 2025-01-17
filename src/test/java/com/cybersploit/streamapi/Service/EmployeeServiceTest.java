package com.cybersploit.streamapi.Service;

import com.cybersploit.streamapi.Entity.Employee;
import com.cybersploit.streamapi.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertEquals(employee, result);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee();
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1);

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateEmployee() {
        Employee existingEmployee = new Employee();
        existingEmployee.setFname("John");
        Employee updatedEmployee = new Employee();
        updatedEmployee.setFname("Jane");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        Employee result = employeeService.updateEmployee(1, updatedEmployee);

        assertEquals("Jane", result.getFname());
        verify(employeeRepository, times(1)).findById(1);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(1);

        employeeService.deleteEmployee(1);

        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetAllUniqueFirstName() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@gmail.com","Shailendra","Sharma","IT",2,"Java",30,80d),
                new Employee(1,"test2@gmail.com","Test","Sharma","IT",2,"Java",30,80d)

        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<String> result = employeeService.getAllUniqueFirstName();

        assertEquals(2, result.size());
        assertTrue(result.contains("Shailendra"));
        assertTrue(result.contains("Test"));
    }

    @Test
    void testGetEmployeeCount() {
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(employees);

        Long result = employeeService.getEmployeeCount();

        assertEquals(2L, result);
    }

    @Test
    void testGetEmployeeWithFirstNameStartingWithS() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@gmail.com","Shailendra","Sharma","IT",2,"Java",30,80d)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeeWithFistNameStartingWithS();

        assertEquals(1, result.size());
        assertEquals("Shailendra", result.get(0).getFname());
    }

    @Test
    void testGetEmployeeWithHighAttendance() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@gmail.com","Shailendra","Sharma","IT",2,"Java",30,80d)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeeWithHighAttendance();

        assertEquals(1, result.size());
        assertEquals(80, result.get(0).getAttendance());
    }

    @Test
    void testGetEmployeeInITDepartment() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@gmail.com","Shailendra","Sharma","IT",2,"Java",30,80d),
                new Employee(2,"test2@gmail.com","Divyansh","Sharma","IT",3,"Python",40,70d)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeeInITDepartment();

        assertEquals(2, result.size());
        assertEquals("IT", result.get(0).getDepartment());
    }

    @Test
    void testGetEmployeeCountByDept() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@gmail.com","Shailendra","Sharma","IT",2,"Java",30,80d),
                new Employee(2,"test2@gmail.com","Divyansh","Sharma","HR",3,"Hiring",40,70d)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        Map<String, Long> result = employeeService.getEmployeeCountByDept();

        assertEquals(1L, result.get("IT"));
        assertEquals(1L, result.get("HR"));
    }

    @Test
    void testGetLastNameByEmailDomain() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@example.in","Shailendra","Sharma","IT",2,"Java",30,80d)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<String> result = employeeService.getLastNameByEmailDomain();

        assertEquals(1, result.size());
        assertEquals("Sharma", result.get(0));
    }

    @Test
    void testGetEmployeeByHighestAttendance() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@gmail.com","Shailendra","Sharma","IT",2,"Java",30,80d),
                new Employee(2,"piyushtest@gmail.com","Piyush","Singh","IT",3,"Python",40,90d),
                new Employee(3,"rajtest@gmail.com","Raj","Verma","IT",4,"Cloud Computing",50,70d)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        Optional<Employee> result = employeeService.getEmployeeByHighestAttendance();

        assertTrue(result.isPresent());
        assertEquals("Piyush", result.get().getFname());
    }

    @Test
    void testGetAverageAttendanceByDepartment() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@gmail.com","Shailendra","Sharma","IT",2,"Java",30,80d),
                new Employee(2,"piyushtest@gmail.com","Piyush","Singh","IT",3,"Python",40,80d),
                new Employee(3,"rajtest@gmail.com","Raj","Verma","IT",4,"Cloud Computing",50,80d)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        Map<String, Double> result = employeeService.getAverageAttendanceByDepartment();

        assertEquals(80, result.get("IT"));

    }

    @Test
    void testGetEmployeeSortedByLastName() {
        List<Employee> employees = Arrays.asList(
                new Employee(1,"test@gmail.com","Shailendra","Sharma","IT",2,"Java",30,80d),
                new Employee(2,"piyushtest@gmail.com","Piyush","Agrawal","IT",3,"Python",40,80d),
                new Employee(3,"rajtest@gmail.com","Raj","Verma","IT",4,"Cloud Computing",50,80d)
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeeSortedByLastName();

        assertEquals("Agrawal", result.get(0).getLname());
        assertEquals("Sharma", result.get(1).getLname());
        assertEquals("Verma", result.get(2).getLname());
    }
}
