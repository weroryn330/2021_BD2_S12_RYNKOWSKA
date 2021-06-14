package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.model.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByUserIdUser(User user);

    Optional<Invoice> findByUserIdUserAndIdInvoice(User user, Integer invoiceId);

    List<Invoice> findByInvoiceDateBetween(Date startDate, Date endDate);
}
