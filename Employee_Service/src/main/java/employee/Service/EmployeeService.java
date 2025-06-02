package employee.Service;

import employee.Repository.EmployeeRepository;
import employee.model.Employee;
import employee.ExceptionHandler.EmployeeNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);

    public Employee createEmployee(Employee employee) {
        if (repository.existsById(employee.getId())) {
            logger.error("Employee already exists with ID: {}", employee.getId());
            throw new IllegalArgumentException("Employee already exists with ID: " + employee.getId());
        }
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) {
            logger.error("No employees found in the database.");
            throw new EmployeeNotFoundException("No employees found in the database.");
        }
        return employees;
    }

    public long getEmployeeCount() {
        long count = repository.count();
        if (count == 0) {
            logger.error("No employees found.");
            throw new EmployeeNotFoundException("No employees found.");
        }
        return count;
    }

    public Employee getEmployeeById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

    }

    public Employee updateEmployee(String id, Employee updated) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
        if (updated.getName() != null) employee.setName(updated.getName());
        if (updated.getDepartment() != null) employee.setDepartment(updated.getDepartment());
        if (updated.getSalary() != null) employee.setSalary(updated.getSalary());
        return repository.save(employee);
    }

    public void deleteEmployee(String id) {
        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException("Cannot delete. Employee not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
