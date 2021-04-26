package polsl.tab.skiresort.model.invoice;

import polsl.tab.skiresort.model.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {

    Optional<List<Invoice>> findInvoicesByIdCustomer(Customer customer);
}
