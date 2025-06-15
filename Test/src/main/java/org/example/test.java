package org.example;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("seenu",65000.0));
        employees.add(new Employee("seenu",70000.0));
        employees.add(new Employee("seenu",25000.0));

        employees.stream().filter(s->s.getSalry()>50000).forEach(System.out::println);
        //employees.stream().sorted(Comparator.reverseOrder()).forEach();

}}
