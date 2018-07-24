package com.dev.finalproject.service.jpa;

import com.dev.finalproject.domain.State;
import com.dev.finalproject.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    @Autowired
    private StateRepository repository;

    public State save(State state) {
        return this.repository.save(state);
    }
    public void delete(State state) {
        this.repository.delete(state);
    }
    public List<State> findAll() {
        return this.repository.findAll();
    }
    public State findById(long id) {
        return this.repository.findById(id).get();
    }
    public boolean exists(long id) {
        return this.repository.existsById(id);
    }

}
