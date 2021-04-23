package polsl.tab.skiresort.model.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.customer.Customer;

import java.util.List;
import java.util.Optional;

@Repository
interface PostgreSQLInvoiceRepository extends InvoiceRepository, JpaRepository<Invoice, Integer> {

    @Override
    Optional<List<Invoice>> findInvoicesByIdCustomer(Customer customer);
}
