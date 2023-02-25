package com.SpringWebFluxCRUD.repository;

import com.SpringWebFluxCRUD.entity.Employee;
import io.r2dbc.spi.Readable;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Repository
@AllArgsConstructor
public class EmployeeDAO {

    //Just for test
    private final DatabaseClient databaseClient;

    private final R2dbcEntityTemplate template;

    private Employee employeeCreator(Readable r){
        return new Employee(r.get("id", Integer.class),r.get("name",String.class),
                r.get("surname",String.class),r.get("salary",Integer.class),r.get("department",String.class));
    }

    public Mono<Employee> getEmployeeById(int id){
        //return template.selectOne(query(where("id").is(id)),Employee.class);
        return template.select(Employee.class).from("employees")
                .matching((query(where("id").is(id)))).one();

    }

    //Just for test
    public Flux<Employee> getAllEmployees(){
        return databaseClient.sql("select * from employees")
                .map(this::employeeCreator).all();
    }

    public Mono<Employee> addEmployee(Employee employee){
        return template.insert(Employee.class).into("employees").using(employee);
    }

    public Mono<Long> deleteEmployeeById(int id) {
        return template.delete(Employee.class)
                .from("employees")
                .matching(query(where("id").is(id))).all();
    }
}
