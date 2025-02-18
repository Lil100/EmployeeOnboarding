package com.example.Onboarding.employee;

import com.example.Onboarding.employee.EmployeeEntity;
import com.example.Onboarding.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create new employee
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    // Verify an employee (change status to VERIFIED)
    public EmployeeEntity verifyEmployee(Long employeeId) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) {
            EmployeeEntity employee = employeeOpt.get();
            if (employee.getStatus() == EmployeeEntity.EmployeeStatus.PENDING_VERIFICATION) {
                employee.setStatus(EmployeeEntity.EmployeeStatus.VERIFIED);
                return employeeRepository.save(employee);
            }
        }
        return null;
    }

    // Delete employee (only if status is PENDING_VERIFICATION)
    public boolean deleteEmployee(Long employeeId) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(employeeId);
        if (employeeOpt.isPresent()) {
            EmployeeEntity employee = employeeOpt.get();
            if (employee.getStatus() == EmployeeEntity.EmployeeStatus.PENDING_VERIFICATION) {
                employeeRepository.delete(employee);
                return true;
            }
        }
        return false;  // Cannot delete verified employee
    }

    // Fetch employees by status (verified or pending)
    public List<EmployeeEntity> getEmployeesByStatus(EmployeeEntity.EmployeeStatus status) {
        return employeeRepository.findByStatus(status);
    }

    // Fetch all employees
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public EmployeeEntity getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(id);
        return employeeOpt.orElse(null);  // Return null if not found
    }
}

