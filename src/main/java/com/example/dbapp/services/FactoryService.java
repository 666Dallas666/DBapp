package com.example.dbapp.services;


import lombok.extern.slf4j.Slf4j;
import com.example.dbapp.models.FactoryEntity;
import org.springframework.stereotype.Service;
import com.example.dbapp.repositories.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class FactoryService {

    private FactoryRepository reps;

    public FactoryService(FactoryRepository reps) {
        this.reps = reps;
    }

    //Вернуть все заводы
    public List<FactoryEntity> getFactories() {
        log.info("Find all items");
        return reps.findAll();
    }

    //Вернуть по id
    public FactoryEntity getFactory(int id) {
        return reps.findById(id).get();
    }

    //Добавить новую запись
    public void insertFactory(FactoryEntity a) {
        log.info("Save item {}", a);
        reps.save(a);
    }

    //Удалить сущность
    public void deleteFactory(FactoryEntity a) {
        if(a.getCataloguesById().isEmpty() && a.getFactoryProvidersById().isEmpty()) {
            log.info("Delete item {}", a);
            reps.delete(a);
        }
        else log.info("Entity has dependencies");
    }

    //Удалить по id
    public void deleteById(int id) {
        if(getFactory(id).getCataloguesById().isEmpty() && getFactory(id).getFactoryProvidersById().isEmpty()) {
            reps.delete(getFactory(id));
        }
        else log.info("Entity has dependencies");
    }


    //Обновить кол-во сотрудников
    public void updateWorkers(int id, int workers){
        FactoryEntity factory = getFactory(id);
        factory.setWorkers(workers);
        reps.save(factory);
    }

    //Обновить доход
    public void updateIncome(int id, int income){
        FactoryEntity factory = getFactory(id);
        factory.setIncome(income);
        reps.save(factory);
    }
}
