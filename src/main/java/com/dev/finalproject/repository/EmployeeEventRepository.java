package com.dev.finalproject.repository;

import com.dev.finalproject.domain.EmployeeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeEventRepository extends JpaRepository<EmployeeEvent, Long> {
    @Query(nativeQuery = true, value = "select * from employee_event ee join employee em on em.id = ee.employee_id join event ev on ev.id = ee.event_id where em.id = :employeeId and ev.date between :dateStart and :dateEnd")
    List<EmployeeEvent> getEmployeeEventsByPeriodAndEmployee(@Param("employeeId") long employeeId, @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd);
}
