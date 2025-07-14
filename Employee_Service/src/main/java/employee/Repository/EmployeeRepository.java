package employee.Repository;

import employee.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query("{ 'salary': { $gt: ?0 } }")
    List<Employee> findBySalaryGreaterThan(Double salary);

    @Query("{'name':?0}")
    List<Employee> findByName(String name);


}

