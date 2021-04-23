package polsl.tab.skiresort.model.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface PostgreSQLEmployeeRepository extends EmployeeRepository, JpaRepository<Employee, Integer> {

    @Override
    Optional<Employee> getEmployeeByIdEmployee(Integer idEmployee);
}
