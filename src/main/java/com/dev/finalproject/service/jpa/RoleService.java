package com.dev.finalproject.service.jpa;

import com.dev.finalproject.domain.Role;
import com.dev.finalproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Role save(Role role) {
        return this.repository.save(role);
    }
    public void delete(Role role) {
        this.repository.delete(role);
    }
    public List<Role> findAll() {
        return this.repository.findAll();
    }
    public Role findById(long id) {
        return this.repository.findById(id).get();
    }
    public boolean exists(long id) {
        return this.repository.existsById(id);
    }

    public Role findByName(String name) {
        return this.repository.findByRole(name);
    }
}
