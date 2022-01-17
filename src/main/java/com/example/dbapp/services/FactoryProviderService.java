package com.example.dbapp.services;

import lombok.extern.slf4j.Slf4j;
import com.example.dbapp.models.FactoryEntity;
import com.example.dbapp.models.FactoryProviderEntity;
import org.springframework.stereotype.Service;
import com.example.dbapp.repositories.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class FactoryProviderService {

    private FactoryProviderRepository reps;

    public FactoryProviderService(FactoryProviderRepository reps) {
        this.reps = reps;
    }

    public void insertConnection(FactoryProviderEntity a) {
        log.info("Save item {}", a);
        reps.save(a);
    }

    public FactoryProviderEntity getFactoryProvider(int id) {
        return reps.findById(id).get();
    }

    public List<FactoryProviderEntity> getСonnections() {
        log.info("Find all items");
        return reps.findAll();
    }

    public void deleteById(int id) {
            reps.delete(getFactoryProvider(id));
    }
}

