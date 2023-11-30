package com.service.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.employee.model.Employee;
import com.service.employee.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/employees")
class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> theEmployee = repository.findById(id);
        return theEmployee.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee theEmployee) {
        repository.save(theEmployee);
        return new ResponseEntity<>(theEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployee = repository.findById(id);
        if (existingEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedEmployee.setId(id);
        repository.save(updatedEmployee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        Optional<Employee> theEmployee = repository.findById(id);
        if (theEmployee.isEmpty()) {
            return new ResponseEntity<>("Employee not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
        return new ResponseEntity<>("Deleted employee with id: " + id, HttpStatus.OK);
    }
}
