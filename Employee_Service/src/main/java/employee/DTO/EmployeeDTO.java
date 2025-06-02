package employee.DTO;

/*🔄 Why have both?
Aspect	                     Employee (Model)	         EmployeeDTO (DTO)
Used for	                 Persistence (DB)	         API input/output
Contains DB annotations	     ✅ @Document, @Id	         ❌
Safe to expose to client     ❌ (can leak internals)	 ✅
Can be reused internally	 ✅	                         ✅
Designed for	             Internal logic, storage	Communication, mapping*/

public class EmployeeDTO {
    private String id;
    private String name;
    private String department;
    private Double salary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}

