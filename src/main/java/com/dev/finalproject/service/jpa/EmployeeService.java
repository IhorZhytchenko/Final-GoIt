package com.dev.finalproject.service.jpa;


import com.dev.finalproject.domain.Employee;
import com.dev.finalproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void update(Employee employee) {
        if(employee.getPassword() == null) {
            employee.setPassword(this.findById(employee.getId()).getPassword());
        }
        if (employee.getRoles().size() == 0) {
            employee.getRoles().add(this.roleService.findByName("USER"));
        }
        employee.setActive(1);
        this.repository.save(employee);
    }
    public Employee save(Employee employee) {
        if(this.repository.findByEmail(employee.getEmail()) != null) {
            return this.repository.findByEmail(employee.getEmail());
        }
        if (employee.getRoles().size() == 0) {
            employee.getRoles().add(this.roleService.findByName("USER"));
        }
        employee.setPassword(encode(employee.getPassword()));
        employee.setActive(1);
        return this.repository.save(employee);
    }
    public void delete(Long id) {

        this.repository.delete(this.findById(id));
    }
    public List<Employee> findAll() {
        return this.repository.findAll();
    }
    public Employee findById(long id) {
        return this.repository.findById(id).get();
    }
    public boolean exists(long id) {
        return this.repository.existsById(id);
    }
    public Employee findByEmail(String email) {

        return this.repository.findByEmail(email);
    }
    public List<Employee> findWorking() {
        return this.repository.findWorking();
    }

    public String encode(String value) {
        return bCryptPasswordEncoder.encode(value);
    }

    public void updateRole(Employee employee) {
        Employee emp = this.findById(employee.getId());
        emp.setRoles(employee.getRoles());
        this.repository.save(emp);
    }
}
