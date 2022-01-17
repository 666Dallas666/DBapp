package com.example.dbapp.services;


import lombok.extern.slf4j.Slf4j;
import com.example.dbapp.models.ProviderEntity;
import org.springframework.stereotype.Service;
import com.example.dbapp.repositories.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ProviderService {

    private ProviderRepository reps;

    public ProviderService(ProviderRepository reps) {
        this.reps = reps;
    }

    //Вернуть всех поставщиков
    public List<ProviderEntity> getProviders() {
        log.info("Find all items");
        return reps.findAll();
    }

    //Вернуть по id
    public ProviderEntity getProvider(int id) {
        return reps.findById(id).get();
    }

    //Добавить новую запись
    public void insertProvider(ProviderEntity a) {
        log.info("Save item {}", a);
        reps.save(a);
    }

    //Удалить сущность
    public void deleteProvider(ProviderEntity a) {
        if(a.getFactoryProvidersById().isEmpty()) {
            log.info("Delete item {}", a);
            reps.delete(a);
        }
        else log.info("Entity has dependencies");
    }

    //Удалить по id
    public void deleteById(int id) {
        if(getProvider(id).getFactoryProvidersById().isEmpty()) {
            reps.delete(getProvider(id));
        }
        else log.info("Entity has dependencies");
    }


    //Обновить кол-во сотрудников
    public void updateWorkers(int id, int workers){
        ProviderEntity provider = getProvider(id);
        provider.setWorkers(workers);
        reps.save(provider);
    }
}
