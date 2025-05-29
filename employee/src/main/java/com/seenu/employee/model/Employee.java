package com.seenu.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    private String name;
    private String department;
    private double salary;

    // Getters and Setters
}
