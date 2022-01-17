package com.example.dbapp.services;


import lombok.extern.slf4j.Slf4j;
import com.example.dbapp.models.ClientEntity;
import org.springframework.stereotype.Service;
import com.example.dbapp.repositories.ClientRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ClientService {
    private ClientRepository reps;
    @PersistenceContext
    EntityManager em;
    public ClientService(ClientRepository reps) {
        this.reps = reps;
    }
    //Вернуть всех клиентов
    public List<ClientEntity> getClients() {
        log.info("Find all items");
        return (List<ClientEntity>) em.createNativeQuery("SELECT * FROM client;",ClientEntity.class).getResultList();
    }

    //Вернуть по id
    public ClientEntity getClient(int id) {
        return reps.findById(id).get();
    }

    //Добавить новую запись
    public void insertClient(ClientEntity a) {
        log.info("Save item {}", a);
        em.createNativeQuery("INSERT INTO client (name, phone_number, license) VALUES(:n,:p,:l);")
                .setParameter("n",a.getName())
                .setParameter("p", a.getPhoneNumber())
                .setParameter("l", a.getLicense()).executeUpdate();
    }

    //Удалить сущность
    public void deleteClient(ClientEntity a) {
        if(a.getOrdersById().isEmpty()) {
            log.info("Delete item {}", a);
            reps.delete(a);
        }
        else log.info("Entity has dependencies");
    }

    //Удалить по id
    public void deleteById(int id) {
            reps.delete(getClient(id));
    }
}
