package polsl.tab.skiresort.model.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PostgreSQLCustomerRepository extends CustomerRepository, JpaRepository<Customer, Integer> {
}
