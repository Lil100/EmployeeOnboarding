package com.example.Onboarding.employee;

import com.example.Onboarding.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    // Find employees by status (verified or pending)
    List<EmployeeEntity> findByStatus(EmployeeEntity.EmployeeStatus status);
}

