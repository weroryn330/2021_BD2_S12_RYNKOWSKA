package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.model.User;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByUserIdUser(User user);

    Optional<Invoice> findByUserIdUserAndIdInvoice(User user, Integer invoiceId);

    List<Invoice> findByInvoiceDateBetween(Date startDate, Date endDate);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM invoices i " +
                    "WHERE i.invoice_date BETWEEN :startDate " +
                    "AND :endDate"
    )
    List<Invoice> findAllWithInvoiceDateBetween(@Param("startDate") java.util.Date startDate,
                                                @Param("endDate") java.util.Date endDate);

    @Query(
            nativeQuery = true,
            value = "DELETE FROM INVOICES i WHERE i.id_invoice = :invoiceId"
    )
    @Transactional
    @Modifying
    void deleteInvoice(@Param("invoiceId") Integer invoiceId);

    Optional<Invoice> findInvoiceByPassListContaining(Pass pass);
}
