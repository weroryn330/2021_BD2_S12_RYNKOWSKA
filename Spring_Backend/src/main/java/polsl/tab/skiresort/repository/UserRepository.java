package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query(
            nativeQuery = true,
            value = "DELETE FROM USERS WHERE email = :email"
    )
    @Modifying
    @Transactional
    void deleteByEmail(@Param("email") String email);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM USERS u, USERS_ROLES ur, ROLES r " +
                    "WHERE u.id_user = ur.users_id_user " +
                    "AND ur.roles_id_role = r.id_role " +
                    "AND (r.role_name like 'ROLE_EMPLOYEE' " +
                    "OR r.role_name like 'ROLE_TECHNICIAN' )"
    )
    Collection<User> findAllEmployees();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM USERS u, USERS_ROLES ur, ROLES r " +
                    "WHERE u.id_user = ur.users_id_user " +
                    "AND ur.roles_id_role = r.id_role " +
                    "AND r.role_name like 'ROLE_USER' "
    )
    Collection<User> findAllSkiers();

    @Query(
            nativeQuery = true,
            value = "SELECT DISTINCT * " +
                    "FROM USERS u, INVOICES i " +
                    "WHERE u.id_user = i.users_id_user " +
                    "AND i.id_invoice = :invoiceId"
    )
    Optional<User> findUserEmailByInvoiceId(@Param("invoiceId") Integer invoiceId);
}
