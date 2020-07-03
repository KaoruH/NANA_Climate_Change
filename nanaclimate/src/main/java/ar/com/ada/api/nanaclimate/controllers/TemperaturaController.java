package ar.com.ada.api.nanaclimate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.nanaclimate.entities.Pais;
import ar.com.ada.api.nanaclimate.entities.Temperatura;
import ar.com.ada.api.nanaclimate.models.request.PostTemperaturaRequest;
import ar.com.ada.api.nanaclimate.models.response.GenericResponse;
import ar.com.ada.api.nanaclimate.services.TemperaturaService;

@RestController
public class TemperaturaController {

    @Autowired
    TemperaturaService temperaturaService;

    @GetMapping("/temperaturas/paises/{paisId}")
    public ResponseEntity<List<Temperatura>> getTemperaturasDePais(@PathVariable Integer paisId){

        List<Temperatura> listaTemperaturas = temperaturaService.buscarPorPaisId(paisId);

        if (listaTemperaturas == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(listaTemperaturas);

    }

    @PostMapping("/temperaturas")
    public ResponseEntity<?> crearTemperatura(@RequestBody PostTemperaturaRequest tempReq){
        
        Temperatura temperatura = new Temperatura();
        temperatura.setPais(temperaturaService.buscarPaisPorId(tempReq.getPaisId()));
        temperatura.setAnio(tempReq.getAnio());
        temperatura.setGrado(tempReq.getGrado());
        

        temperaturaService.crearTemperatura(temperatura);
        
        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = temperatura.getTemperaturaId();
        resp.message = "Temperatura generada con exito";

        return ResponseEntity.ok(resp);

    }

    @DeleteMapping("/temperaturas/{id}")
    public ResponseEntity<?> borrarTemperatura(@PathVariable Integer id){

        
    }


    
}