package com.SpringWebFluxCRUD.controller;

import com.SpringWebFluxCRUD.entity.Employee;
import com.SpringWebFluxCRUD.repository.EmployeeDAO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class SomeController {

    private final EmployeeDAO employeeDAO;

    @GetMapping("/employee/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable int id){
        return employeeDAO.getEmployeeById(id)
                .defaultIfEmpty(new Employee(0,"Not","found",0,""));
    }

    @GetMapping("/employees")
    public Flux<Employee> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }
    @PostMapping("/employee")
    public Mono<Employee> addEmployee(@RequestBody Employee employee){
        return employeeDAO.addEmployee(employee);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Long> deleteEmployee(@PathVariable int id){
        return employeeDAO.deleteEmployeeById(id);
    }


}
