package employee.Controller;

import employee.Repository.EmployeeRepository;
import employee.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    //Insert Employee
    /*@PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.info("Creating a new Employee: {}", employee);
        return repository.save(employee);
    }*/

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        if (repository.existsById(employee.getId())){
            logger.warn("User Already Existed with Employee ID");
            return ResponseEntity.status(409).build();
        }else {
            Employee savedEmployee = repository.save(employee);
            logger.info("Created a new Employee: {}", employee);
            return ResponseEntity.status(201).body(savedEmployee);
        }
    }


    //Get All Employees
    /*@GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Get All Employees from DB");
        return repository.findAll();
    }*/

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = repository.findAll();
        if(employees.isEmpty()){
            logger.warn("Employees not present in the DB: {}",employees);
            return ResponseEntity.status(404).body(null);
        }else{
            logger.info("List of Employees: {}",employees);
            return ResponseEntity.ok().build();
        }
    }

    //Get Single Employee
    @GetMapping("employeeId/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id, Employee employee) {
        logger.info("Get Employee by EmployeeID API called from Client with path: /employeeId/{}", id);
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Employee with ID: {} not found", id);
                    ResponseEntity.notFound().build();
                    return ResponseEntity.notFound().build();
                });
    }

    /*@GetMapping("employeeId/{id}")
    public ResponseEntity<String> getSingleEmployee(@PathVariable String id){
        if(repository.findById(id).isPresent()){
            logger.info("Employee fetched from DB as ID: {}",id);
            return ResponseEntity.ok().build();
        }else{
            logger.warn("Employee not Found in DB");
            return ResponseEntity.status(404).body("Not Found in DB");
        }
    }*/

    /*@GetMapping("employeeId/{id}")
    public Optional<Employee> getAllEmployees(@PathVariable String id){
        logger.info("Get Employee by EmpId from DB: {}",id);
        return repository.findById(id);
    }*/

    //Patch Employee
    /*@PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updated) {
        return repository.findById(id).map(employee -> {
            employee.setName(updated.getName());
            employee.setDepartment(updated.getDepartment());
            employee.setSalary(updated.getSalary());
            return ResponseEntity.ok(repository.save(employee));
        }).orElse(ResponseEntity.notFound().build());
    }*/

    //Patch Employee
    @PatchMapping("employeeId/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updated) {
        logger.info("Employee updated with ID: {} with Data: {}", id, updated);
        return repository.findById(id).map(employee -> {
            if (updated.getName() != null) employee.setName(updated.getName());
            if (updated.getDepartment() != null) employee.setDepartment(updated.getDepartment());
            if (updated.getSalary() != null) employee.setSalary(updated.getSalary());
            Employee saved = repository.save(employee);
            logger.info("Updated employee: {}", saved);
            return ResponseEntity.ok(repository.save(employee));
        }).orElseGet(() -> {
            logger.warn("Employee with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        });
    }

    //Delete Employee
    @DeleteMapping("employeeId/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("Deleted the Employees from DB with ID: {}", id);
            return ResponseEntity.ok("Employee Deleted Successfully.....");
        } else {
            logger.warn("Employee is not existed in the DB: {}", id);
            return ResponseEntity.status(404).body("Employee not found.....");
        }
    }
}

