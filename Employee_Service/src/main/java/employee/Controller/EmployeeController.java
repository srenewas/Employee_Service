package employee.Controller;


import employee.DTO.EmployeeDTO;
import employee.EmployeeApplication;
import employee.Mapper.EmployeeMapper;
import employee.Repository.EmployeeRepository;
import employee.model.Employee;
import employee.Service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

   @PostMapping
   public ResponseEntity<EmployeeDTO> createEmployee( @RequestBody EmployeeDTO dto) {
       Employee saved = service.createEmployee(EmployeeMapper.toEntity(dto)); // uses service
       logger.info("Employee Created Successfully with ID: {}",saved.getId());
       return ResponseEntity.status(201).body(EmployeeMapper.toDTO(saved));
   }


    @GetMapping("/allEmployees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        logger.info("Fetching all employees");
        List<Employee> employees = service.getAllEmployees();
        return ResponseEntity.ok(EmployeeMapper.toDTOList(employees));
    }

    @GetMapping("/allEmployees/count")
    public ResponseEntity<Long> getEmployeeCount() {
        long count = repository.count();
        logger.info("Counting employees: {}", count);
        return ResponseEntity.ok(service.getEmployeeCount());
    }

    @GetMapping("/employeeId/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable String id) {
        logger.info("Fetching employee by ID: {}", id);
        return ResponseEntity.ok(EmployeeMapper.toDTO(service.getEmployeeById(id)));
    }

    @PatchMapping("/employeeId/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String id, @RequestBody EmployeeDTO dto) {
        logger.info("Updating employee ID: {} with data: {}", id, dto);
        Employee updated = service.updateEmployee(id, EmployeeMapper.toEntity(dto));
        return ResponseEntity.ok(EmployeeMapper.toDTO(updated));
    }

    @DeleteMapping("/employeeId/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        logger.info("Deleting employee ID: {}", id);
        service.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }
}
