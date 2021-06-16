package polsl.tab.skiresort.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import polsl.tab.skiresort.model.SkiLiftSchedule;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface SkiLiftScheduleRepository extends JpaRepository<SkiLiftSchedule, Integer> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM ski_lift_schedules s " +
            "WHERE s.ski_lift_id_ski_lift=:skiLiftId " +
            "AND CURRENT_DATE BETWEEN s.start_date AND s.end_date")
    Optional<SkiLiftSchedule> findSkiLiftScheduleBySkiLiftIdSkiLift(@Param("skiLiftId") Integer skiLiftId);

    @Query(nativeQuery = true,
    value="SELECT * FROM ski_lift_schedules s " +
            "WHERE CURRENT_DATE BETWEEN s.start_date AND s.end_date")
    List<SkiLiftSchedule> findAllCurrent();

    @Query(nativeQuery = true,
            value="SELECT * FROM ski_lift_schedules s " +
                    "WHERE CURRENT_DATE BETWEEN s.start_date AND s.end_date AND s.ski_lift_id_ski_lift=:skiLiftId")
    Optional<SkiLiftSchedule> findCurrentBySkiLiftId(@Param("skiLiftId") Integer skiLiftId);

    @Query(nativeQuery = true,
    value = "SELECT * FROM ski_lift_schedules s " +
            "WHERE s.end_date >= :date AND s.ski_lift_id_ski_lift=:skiLiftId")
    List<SkiLiftSchedule> findBySkiLiftIdWithEndDateAfter(@Param("date") Date date, @Param("skiLiftId") Integer skiLiftId);

    @Query(nativeQuery = true,
            value = "SELECT * FROM ski_lift_schedules s " +
                    "WHERE s.end_date > :date " +
                    "AND CURRENT_DATE NOT BETWEEN s.start_date AND s.end_date " +
                    "AND s.ski_lift_id_ski_lift=:skiLiftId")
    List<SkiLiftSchedule> findBySkiLiftIdWithEndDateAfterWithoutCurrent(@Param("date") Date date, @Param("skiLiftId") Integer skiLiftId);

public interface SkiLiftScheduleRepository extends JpaRepository<SkiLiftSchedule, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT ss FROM ski_lift_schedules ss, ski_lifts sl " +
                    "WHERE ss.ski_lift_id_ski_lift = sl.id_ski_lift " +
                    "AND sl.id_ski_lift = :skiLiftId " +
                    "AND :currentDate >= ss.start_date " +
                    "AND :currentDate <= ss.end_date"
    )
    Optional<SkiLiftSchedule> findCurrentScheduleForSkiLiftId(
            @Param("skiLiftId") Integer id,
            @Param("currentDate") Date startDate
    );
}
