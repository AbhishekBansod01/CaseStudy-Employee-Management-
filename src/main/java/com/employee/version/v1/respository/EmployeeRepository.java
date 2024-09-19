package com.employee.version.v1.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.version.v1.entities.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

	public Optional<EmployeeEntity> findEmployeeByEmpId(String empId);

	public void deleteEmployeeByEmpId(String empId);

}
