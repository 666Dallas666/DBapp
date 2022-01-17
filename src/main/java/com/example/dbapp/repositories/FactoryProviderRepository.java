package com.example.dbapp.repositories;

import com.example.dbapp.models.FactoryProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryProviderRepository extends JpaRepository<FactoryProviderEntity, Integer> {
}
