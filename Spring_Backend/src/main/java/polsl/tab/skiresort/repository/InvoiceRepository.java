package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.model.Pass;
import polsl.tab.skiresort.model.User;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByUserIdUser(User user);
}
