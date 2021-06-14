package polsl.tab.skiresort.api.employee.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import polsl.tab.skiresort.api.employee.request.SkiLiftScheduleRequest;
import polsl.tab.skiresort.model.SkiLiftSchedule;
import polsl.tab.skiresort.repository.SkiLiftScheduleRepository;

import java.util.List;

@Aspect
@Component
public class SkiLiftScheduleValidation {

    private final SkiLiftScheduleRepository skiLiftScheduleRepository;

    public SkiLiftScheduleValidation(SkiLiftScheduleRepository skiLiftScheduleRepository) {
        this.skiLiftScheduleRepository = skiLiftScheduleRepository;
    }

    @Before("execution(* polsl.tab.skiresort.api.employee.SkiLiftScheduleApi.addNewSkiLiftSchedule(..))")
    void validateNewSkiLiftSchedule(JoinPoint joinPoint){

        SkiLiftScheduleRequest skiLiftScheduleRequest = (SkiLiftScheduleRequest) joinPoint.getArgs()[0];
        if(skiLiftScheduleRequest.getStartDate().compareTo(skiLiftScheduleRequest.getEndDate()) < 0){

            List<SkiLiftSchedule> oldSchedules = skiLiftScheduleRepository.findBySkiLiftIdWithEndDateAfter(
                    skiLiftScheduleRequest.getStartDate(), skiLiftScheduleRequest.getSkiLiftId());
            if(oldSchedules.isEmpty() ||
                    oldSchedules.stream().allMatch(
                    schedule -> schedule.getEndDate().compareTo(skiLiftScheduleRequest.getStartDate()) < 0 ||
                            schedule.getStartDate().compareTo(skiLiftScheduleRequest.getEndDate()) > 0)) {
                return;
            }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedules collision");
        }else
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End date cannot be before start date");
    }

    @Before("execution(* polsl.tab.skiresort.api.employee.SkiLiftScheduleApi.editSkiLiftSchedule(..))")
    void validateEditingCurrentSkiLiftSchedule(JoinPoint joinPoint) throws Throwable {

        SkiLiftScheduleRequest skiLiftScheduleRequest = (SkiLiftScheduleRequest) joinPoint.getArgs()[0];
        if(skiLiftScheduleRequest.getStartDate().compareTo(skiLiftScheduleRequest.getEndDate()) < 0){

            List<SkiLiftSchedule> oldSchedules = skiLiftScheduleRepository.findBySkiLiftIdWithEndDateAfterWithoutCurrent(
                    skiLiftScheduleRequest.getStartDate(),skiLiftScheduleRequest.getSkiLiftId());

            if(oldSchedules.isEmpty() ||
                    oldSchedules.stream().allMatch(
                            schedule -> schedule.getEndDate().compareTo(skiLiftScheduleRequest.getStartDate()) < 0 ||
                                    schedule.getStartDate().compareTo(skiLiftScheduleRequest.getEndDate()) > 0)) {
                return;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Schedules collision");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End date cannot be before start date");
    }

}
