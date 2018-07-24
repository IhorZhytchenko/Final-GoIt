package com.dev.finalproject.service.jpa;

import com.dev.finalproject.domain.EmployeeEvent;
import com.dev.finalproject.domain.Event;
import com.dev.finalproject.repository.EmployeeEventRepository;
import com.dev.finalproject.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private EmployeeEventRepository employeeEventRepository;
    @Autowired
    private EmployeeService employeeService;

    public Event save(Event event) {
        event.setDate(LocalDate.now());
        this.repository.save(event);
        for (EmployeeEvent ee: event.getEmployeeEvents()) {
           ee.setEvent(event);
           this.employeeEventRepository.save(ee);
        }
        return event;
    }
    public void delete(Event event) {
        this.repository.delete(event);
    }
    public List<Event> findAll() {
        return this.repository.findAll();
    }
    public Event findById(long id) {
        return this.repository.findById(id).get();
    }
    public boolean exists(long id) {
        return this.repository.existsById(id);
    }

    public Event getIntermediateEvent(Event event, Long[] employees) {
        for (Long id: employees) {
            EmployeeEvent employeeEvent = new EmployeeEvent();
            employeeEvent.setEvent(event);
            employeeEvent.setEmployee(this.employeeService.findById(id));
            event.getEmployeeEvents().add(employeeEvent);
        }
        return event;
    }

    public List<EmployeeEvent> getEmployeeEventsByPeriodAndEmployee(long employeeId, String dateStart, String dateFinish) {
        return this.employeeEventRepository.getEmployeeEventsByPeriodAndEmployee(employeeId,dateStart,dateFinish);
    }
}
