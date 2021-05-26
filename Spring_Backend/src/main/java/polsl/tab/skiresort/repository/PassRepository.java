package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.Pass;

import java.util.Collection;

@Repository
public interface PassRepository extends JpaRepository<Pass, Integer> {

    @Query (
            nativeQuery = true,
            value = "SELECT * FROM PASSES WHERE end_date > NOW() OR uses_left != 0"
    )
    Collection<Pass> getAllActivePasses();
}
