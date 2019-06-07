package com.ems.springboot.dao;

import com.ems.springboot.model.Employee;

import java.util.List;

public interface EmployeeDao {
    void saveEmployee(Employee employee);

    List<Employee> findAllEmployees();

    void deleteEmployee(int id);

    Employee findEmployee(String email);

    void updateEmployee(Employee employee);
}
