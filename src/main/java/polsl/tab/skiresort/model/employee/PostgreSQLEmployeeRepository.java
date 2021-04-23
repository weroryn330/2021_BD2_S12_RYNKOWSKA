package polsl.tab.skiresort.model.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PostgreSQLEmployeeRepository extends EmployeeRepository, JpaRepository<Employee, Integer> {
}
