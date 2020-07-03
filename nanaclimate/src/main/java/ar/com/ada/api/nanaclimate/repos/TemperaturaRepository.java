package ar.com.ada.api.nanaclimate.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.nanaclimate.entities.Temperatura;

@Repository
public interface TemperaturaRepository extends JpaRepository<Temperatura, Integer>{
    
}