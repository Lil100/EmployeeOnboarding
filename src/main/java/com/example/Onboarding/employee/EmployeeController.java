package com.example.Onboarding.employee;

import com.example.Onboarding.employee.EmployeeEntity;
import com.example.Onboarding.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Endpoint for creating an employee
    @PostMapping("/create")
    public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee) {
        EmployeeEntity createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    // Endpoint for verifying an employee (admin action)
    @PostMapping("/verify/{id}")
    public ResponseEntity<String> verifyEmployee(@PathVariable Long id) {
        EmployeeEntity verifiedEmployee = employeeService.verifyEmployee(id);
        if (verifiedEmployee != null) {
            return ResponseEntity.ok("Employee verified successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification failed.");
    }

    // Endpoint for deleting an employee (only pending employees can be deleted)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            return ResponseEntity.ok("Employee deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete a verified employee.");
    }

    // Endpoint to fetch employees by status (admin can fetch pending or verified)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByStatus(@PathVariable String status) {
        EmployeeEntity.EmployeeStatus employeeStatus;
        try {
            employeeStatus = EmployeeEntity.EmployeeStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<EmployeeEntity> employees = employeeService.getEmployeesByStatus(employeeStatus);
        return ResponseEntity.ok(employees);
    }

    // Endpoint to fetch all employees (admin only)
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
