package com.goit.dao;

import com.goit.model.Employee;
import com.goit.model.Position;

import java.sql.Date;
import java.util.List;

public interface EmployeeDao {

    void createEmployee(Employee employee);

    Employee findEmployeeById(int id);

    List<Employee> findEmployeeByLastName(String lastName);

    List<Employee> findEmployeeByFirstName(String firstName);

    Employee findEmployeeByFullName(String lastName, String firstName);

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployeesByPosition(Position position);

    void deleteEmployee(int id);

    void updateEmployeeLastName(int id, String newEmployeeLastName);

    void updateEmployeeFirstName(int id, String newEmployeeFirstName);

    void updateEmployeeBirthday(int id, Date newEmployeeBirthday);

    void updateEmployeePhone(int id, String newEmployeePhone);

    void updateEmployeePositionId(int id, Position newPosition);

    void updateEmployeeSalary(int id, float newEmployeeSalary);

}
