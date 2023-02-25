package com.SpringWebFluxCRUD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    private int id;
    private String name;

    private String surname;

    private int salary;

    private String department;

}
