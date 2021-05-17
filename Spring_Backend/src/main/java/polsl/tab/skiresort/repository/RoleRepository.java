package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.Role;

@Repository
interface RoleRepository extends JpaRepository<Role, Integer> {
}
