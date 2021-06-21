package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.Invoice;
import polsl.tab.skiresort.model.Pass;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface PassRepository extends JpaRepository<Pass, Integer> {

    @Query (
            nativeQuery = true,
            value = "SELECT * FROM PASSES WHERE (end_date >= NOW() OR uses_left != 0) AND blocked = FALSE"
    )
    Collection<Pass> getAllActivePasses();

    @Query (
            nativeQuery = true,
            value = "SELECT * FROM PASSES p " +
                    "INNER JOIN INVOICES i on i.id_invoice = p.invoices_id_invoice " +
                    "INNER JOIN USERS u on u.id_user = i.users_id_user " +
                    "AND u.id_user = :userId"
    )
    Collection<Pass> getAllPassesForUser(@Param("userId") Integer userId);

    @Query (
            nativeQuery = true,
            value = "SELECT * FROM PASSES p " +
                    "INNER JOIN INVOICES i on i.id_invoice = p.invoices_id_invoice " +
                    "INNER JOIN USERS u on u.id_user = i.users_id_user " +
                    "AND u.id_user = :userId " +
                    "AND (p.end_date >= NOW() " +
                    "OR p.uses_left != 0) " +
                    "AND p.blocked = FALSE"
    )
    Collection<Pass> getAllActivePassesForUser(@Param("userId") Integer userId);

    @Query (
            nativeQuery = true,
            value = "SELECT * FROM PASSES p " +
                    "INNER JOIN INVOICES i on i.id_invoice = p.invoices_id_invoice " +
                    "INNER JOIN USERS u on u.id_user = i.users_id_user " +
                    "AND u.id_user = :userId " +
                    "AND p.id_pass = :passId"
    )
    Optional<Pass> getUserPass(@Param("userId") Integer userId, @Param("passId") Integer passId);

    Optional<Pass> deleteByInvoicesIdInvoiceAndIdPass(Invoice invoice, Integer passId);

    @Query(
            nativeQuery = true,
            value = "DELETE FROM PASSES p WHERE p.id_pass = :passId"
    )
    @Transactional
    @Modifying
    void deletePass(@Param("passId") Integer passId);

    @Query(
            nativeQuery = true,
            value = "SELECT DISTINCT p.* FROM PASSES p " +
                    "INNER JOIN invoices i on i.id_invoice = p.invoices_id_invoice " +
                    "INNER JOIN users us on us.id_user = i.users_id_user " +
                    "AND us.id_user = :userId " +
                    "INNER JOIN usages u on p.id_pass = u.passes_id_invoice_item " +
                    "AND :startDate <= u.use_timestamp " +
                    "AND :endDate >= u.use_timestamp " +
                    "AND p.blocked = FALSE"
    )
    @QueryHints(value = { @QueryHint(name = "HINT_PASS_DISTINCT_THROUGH", value = "false")})
    Collection<Pass> getPassesUsedBetweenTimestamps(
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate,
            @Param("userId") Integer userId
    );
}
