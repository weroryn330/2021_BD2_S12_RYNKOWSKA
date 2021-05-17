package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.AgeDiscount;

@Repository
interface AgeDiscountRepostory extends JpaRepository<AgeDiscount, Integer> {
}
