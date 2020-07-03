package ar.com.ada.api.nanaclimate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nanaclimate.repos.TemperaturaRepository;

@Service
public class TemperaturaService {

    @Autowired
    TemperaturaRepository temperaturaRepo;

    
    
}