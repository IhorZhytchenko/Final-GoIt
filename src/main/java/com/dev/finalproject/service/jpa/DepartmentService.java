package com.dev.finalproject.service.jpa;

import com.dev.finalproject.domain.Department;
import com.dev.finalproject.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public Department save(Department department) {
        return this.repository.save(department);
    }
    public String delete(Long id) {
        if(this.exists(id)) {
            Department department = this.findById(id);
            if(department.getEmployees().size() == 0) {
                this.repository.delete(department);
                return "deletion was successful";
            } else {
                return "delete error, the object is used";
            }
        } else {
            return "delete error";
        }

    }
    public List<Department> findAll() {
        return this.repository.findAll();
    }
    public Department findById(long id) {
        return this.repository.findById(id).get();
    }
    public boolean exists(long id) {
        return this.repository.existsById(id);
    }

}
