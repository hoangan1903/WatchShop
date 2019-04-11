package com.seuit.spring.watchshop.rest;

import com.seuit.spring.watchshop.entity.*;
import com.seuit.spring.watchshop.service.EmployeeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
@RestController
@RequestMapping(value = {"/rest"})
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    String newEmployee(@Valid @RequestBody EmployeeApi employeeApi) {
        employeeService.saveOrUpdateEmployee(employeeApi, null);
        return "Add success";
    }

    @DeleteMapping("/employees/{id}")
    String deleteEmployee(@PathVariable(value = "id") @Min(1) Integer id) {
        employeeService.deleteEmployeeById(id);
        return "Delete Success";
    }

    @PutMapping("/employees/{id}")
    String updateEmployee(@Valid @RequestBody EmployeeApi employeeApi, BindingResult result, @PathVariable(value = "id") @Min(1) Integer id) {
        employeeService.saveOrUpdateEmployee(employeeApi,id);
        return "Update Success";
    }

    @GetMapping(value="/employees",params = {"page","size"})
    List<Employee> findAllEmployeeByPaginated(@RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size) {
        return employeeService.findPaginated(page,size);
    }

    @GetMapping("/employees/count")
    Long getCountTotalEmployee() {
        return employeeService.countEmployee();
    }

    @GetMapping(value="/employees")
    List<Employee> findAllEmployee() {
        return employeeService.listEmployee();
    }

    @GetMapping("/employees/{id}")
    Employee findEmployeeByEmployeeId(@PathVariable(value = "id") @Min(1) Integer id) throws NotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employees/find/{keyword}")
    List<Employee> findEmployeeByKeyword(@PathVariable(value = "keyword") String keyword) {
        return employeeService.getListEmployeeBykeyword(keyword);
    }
}
