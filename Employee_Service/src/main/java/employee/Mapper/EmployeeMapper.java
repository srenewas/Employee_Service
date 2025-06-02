package employee.Mapper;


import employee.DTO.EmployeeDTO;
import employee.model.Employee;

import java.util.List;
import java.util.stream.Collectors;


/*✅ Why use a Mapper class?
In a Spring Boot app, we often:
Use the entity (Employee) for persistence (e.g., database operations).
Use the DTO (EmployeeDTO) for data transfer through APIs.
The EmployeeMapper bridges these two representations.*/


public class EmployeeMapper {

   // Converts a single Employee object to an EmployeeDTO
   // Used when sending data to the client (API response)

    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) return null;
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDepartment(employee.getDepartment());
        dto.setSalary(employee.getSalary());
        return dto;
    }

   // Converts an EmployeeDTO back to Employee
   // Used when receiving data from the client (API request)

    public static Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        return employee;
    }

    //Converts a list of Employee objects to a list of EmployeeDTO objects
    //Useful when returning a full list of employees from a service

    public static List<EmployeeDTO> toDTOList(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Converts a list of DTOs to a list of domain entities
    //Could be used when accepting batch input (e.g., bulk employee upload)

    public static List<Employee> toEntityList(List<EmployeeDTO> dtos) {
        return dtos.stream()
                .map(EmployeeMapper::toEntity)
                .collect(Collectors.toList());
    }
}


/*
💡 Benefits of Using a Mapper
Keeps controller/service layers clean and focused on logic, not conversion.
Helps avoid accidentally exposing internal fields (e.g., DB IDs, passwords).
Makes it easy to evolve your API without tightly coupling it to your DB model.
Enables better unit testing by isolating mapping logic.*/
