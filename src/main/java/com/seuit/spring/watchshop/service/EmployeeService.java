package com.seuit.spring.watchshop.service;

import com.seuit.spring.watchshop.entity.Employee;
import com.seuit.spring.watchshop.entity.EmployeeApi;
import javassist.NotFoundException;
import java.util.List;

public interface EmployeeService {
    void deleteEmployeeById(Integer id);
    void saveOrUpdateEmployee(EmployeeApi employeeApi,Integer id);
    List<Employee> listEmployee( );
    Employee getEmployeeById(Integer id) throws NotFoundException;
    List<Employee> findPaginated(Integer page,Integer size);
    Long countEmployee();
    List<Employee> getListEmployeeBykeyword(String keyword);
}
