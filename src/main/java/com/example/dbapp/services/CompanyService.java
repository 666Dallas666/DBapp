package com.example.dbapp.services;


import com.example.dbapp.models.CatalogueEntity;
import com.example.dbapp.models.ClientEntity;
import lombok.extern.slf4j.Slf4j;
import com.example.dbapp.models.CompanyEntity;
import org.springframework.stereotype.Service;
import com.example.dbapp.repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class CompanyService {

    private CompanyRepository reps;
    @PersistenceContext
    EntityManager em;
    public CompanyService(CompanyRepository reps) {
        this.reps = reps;
    }
    //Вернуть все компании
    public List<CompanyEntity> getCompanies() {
        log.info("Find all items");
        return (List<CompanyEntity>) em.createNativeQuery("SELECT * FROM company;",CompanyEntity.class).getResultList();
    }

    //Вернуть по id
    public CompanyEntity getCompany(int id) {
        return reps.findById(id).get();
    }

    //Добавить новую запись
    public void insertCompany(CompanyEntity a) {
        log.info("Save item {}", a);
        em.createNativeQuery("INSERT INTO company (name, workers, address, director, founded, income) VALUES(:name, :workers, :address, :director, :founded, :income);")
                .setParameter("name",a.getName())
                .setParameter("workers", a.getWorkers())
                .setParameter("address", a.getAddress())
                .setParameter("director", a.getDirector())
                .setParameter("founded", a.getFounded())
                .setParameter("income", a.getIncome())
                .executeUpdate();
    }

    //Фильтр по цене
    public List<CompanyEntity> filterCompanies(int workers1, int workers2){
        return (List<CompanyEntity>) em.createNativeQuery("SELECT * FROM company WHERE workers > :workers1 and workers < :workers2 ;", CompanyEntity.class)
                .setParameter("workers1", workers1)
                .setParameter("workers2", workers2)
                .getResultList();
    }
    //Сортировка по возрастанию цены
    public List<CompanyEntity> ascCompanies(){
        return (List<CompanyEntity>) em.createNativeQuery("SELECT * FROM company ORDER BY workers ASC;",CompanyEntity.class).getResultList();
    }
    //Сортировка по убыванию цены
    public List<CompanyEntity> descCompanies(){
        return (List<CompanyEntity>) em.createNativeQuery("SELECT * FROM company ORDER BY workers DESC;",CompanyEntity.class).getResultList();
    }

    //Удалить сущность
    public void deleteCompany(CompanyEntity a) {
        if(a.getFactoriesById().isEmpty()) {
            log.info("Delete item {}", a);
            reps.delete(a);
        }
        else log.info("Entity has dependencies");
    }

    //Удалить по id
    public void deleteById(int id) {
        if(getCompany(id).getFactoriesById().isEmpty()) {
            reps.delete(getCompany(id));
        }
        else log.info("Entity has dependencies");
    }

    //Обновить кол-во сотрудников
    public void updateWorkers(int id, int workers){
        CompanyEntity comp = getCompany(id);
        comp.setWorkers(workers);
        reps.save(comp);
    }

    //Обновить доход
    public void updateIncome(int id, int income){
        CompanyEntity comp = getCompany(id);
        comp.setIncome(income);
        reps.save(comp);
    }
}
