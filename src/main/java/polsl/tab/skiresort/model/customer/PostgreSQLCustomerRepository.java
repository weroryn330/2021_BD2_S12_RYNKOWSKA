package polsl.tab.skiresort.model.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface PostgreSQLCustomerRepository extends CustomerRepository, JpaRepository<Customer, Integer> {

    @Override
    Optional<Customer> findCustomerByIdCustomer(Integer idCustomer);

}
