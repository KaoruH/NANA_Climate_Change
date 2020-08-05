package ar.com.ada.api.nanaclimate.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nanaclimate.entities.Temperatura;
import ar.com.ada.api.nanaclimate.repos.TemperaturaRepository;

@Service
public class TemperaturaService {

    @Autowired
    TemperaturaRepository temperaturaRepo;

    @Autowired
    PaisService paisService;

    public Temperatura createTemperatura(Integer paisId, Integer anio, double grado) {

        Temperatura temperatura = new Temperatura();

        temperatura.setPais(paisService.buscarPorId(paisId));
        temperatura.setAnio(anio);
        temperatura.setGrado(grado);

        temperaturaRepo.save(temperatura);

        return temperatura;

    }

    public void grabar(Temperatura temperatura) {

        temperaturaRepo.save(temperatura);
    }

    public boolean existe(Integer id) {

        Temperatura temperatura = buscarPorId(id);

        return temperatura != null;
    }

    public Temperatura buscarPorId(Integer id) {

        Optional<Temperatura> b = temperaturaRepo.findById(id);

        if (b.isPresent())
            return b.get();
        return null;

    }

}