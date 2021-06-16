package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.Role;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);

    @Modifying
    @Transactional
    void deleteAll();

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ROLES " +
                    "INNER JOIN users_roles ur on roles.id_role = ur.roles_id_role AND ur.users_id_user = :userId"
    )
    Collection<Role> findByUserId(Integer userId);

    @Query(
            nativeQuery = true,
            value = "DELETE FROM users_roles ur WHERE ur.users_id_user = :userId"
    )
    @Modifying
    @Transactional
    void deleteRolesByUserId(@Param("userId") Integer userId);
}
