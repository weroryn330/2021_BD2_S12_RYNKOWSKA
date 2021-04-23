package polsl.tab.skiresort.model.customer;

import java.util.Optional;

public interface CustomerRepository {

    // Save customer in database
    Customer save(Customer entity);

    Optional<Customer> findCustomerByIdCustomer(Integer idCustomer);
}
