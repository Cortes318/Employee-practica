package com.service.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.service.employee.model.Employee;
import com.service.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
class EmployeController {
    @Autowired
    private  EmployeeRepository repository;

    //Get all Employe
    @GetMapping("/employees")
    public List<Employee> getAll(){
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployee(@PathVariable Integer id) throws Exception {
        Optional<Employee> theEmployee = repository.findById(id);

        if(theEmployee.isEmpty())
            throw new Exception("Employee id not found - "+id);

        return theEmployee;
    }

    //add mapping for post - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        repository.save(theEmployee);
        return theEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        repository.save(theEmployee);
        return theEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Integer id) throws Exception {
        Optional<Employee> theEmployee = repository.findById(id);

        if(theEmployee.isEmpty())
            throw new Exception("Employee id not found - "+id);
        repository.deleteById(id);
        return "Delete employee id -"+id;
    }
}
