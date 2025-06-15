package org.example;

public class Employee {
    String name;
    Double salry;

    public Employee(String name, Double salry) {
        this.name = name;
        this.salry = salry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalry() {
        return salry;
    }

    public void setSalry(Double salry) {
        this.salry = salry;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salry=" + salry +
                '}';
    }
}
