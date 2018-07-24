package com.dev.finalproject.service.jpa;

import com.dev.finalproject.domain.Position;
import com.dev.finalproject.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
    @Autowired
    private PositionRepository repository;

    public Position save(Position position) {
        return this.repository.save(position);
    }
    public String delete(Long id) {
        if(this.exists(id)) {
            Position position = this.findById(id);
            if(position.getEmployees().size() == 0) {
                this.repository.delete(position);
                return "deletion was successful";
            } else {
                return "delete error, the object is used";
            }
        } else {
            return "delete error";
        }

    }
    public List<Position> findAll() {
        return this.repository.findAll();
    }
    public Position findById(long id) {
        return this.repository.findById(id).get();
    }
    public boolean exists(long id) {
        return this.repository.existsById(id);
    }

}
