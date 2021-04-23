package polsl.tab.skiresort.model.employee;

import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee customer);

    Optional<Employee> getEmployeeByIdEmployee(Integer idEmployee);

}
