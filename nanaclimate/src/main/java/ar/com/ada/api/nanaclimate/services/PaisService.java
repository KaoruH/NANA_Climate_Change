package ar.com.ada.api.nanaclimate.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.nanaclimate.entities.Pais;
import ar.com.ada.api.nanaclimate.repos.PaisRepository;

@Service
public class PaisService {

    @Autowired
    PaisRepository paisRepo;

    public List<Pais> buscarTodos() {

        return paisRepo.findAll();

    }

    public Pais buscarPorId(Integer id) {

        Optional<Pais> b = paisRepo.findById(id);

        if (b.isPresent())
            return b.get();
        return null;

    }

    public boolean actualizarNombre(Pais pais, String nombre) {

        pais.setNombre(nombre);

        grabar(pais);

        return true;

    }

    public boolean crearPais(Pais pais) {

        if (existe(pais.getPaisId()))
            return false;

        grabar(pais);
        return true;
    }

    public void grabar(Pais pais) {

        paisRepo.save(pais);
    }

    public boolean existe(Integer id) {

        Pais pais = buscarPorId(id);

        return pais != null;
    }

}