package ar.com.ada.api.nanaclimate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.nanaclimate.entities.*;
import ar.com.ada.api.nanaclimate.models.request.PostTemperaturaRequest;
import ar.com.ada.api.nanaclimate.models.response.GenericResponse;
import ar.com.ada.api.nanaclimate.services.*;

@RestController
public class TemperaturaController {

    @Autowired
    TemperaturaService temperaturaService;
    @Autowired
    PaisService paisService;

    @GetMapping("/temperaturas/paises/{paisId}")
    public ResponseEntity<List<Temperatura>> getTemperaturasDePais(@PathVariable Integer paisId) {

        Pais pais = paisService.buscarPorId(paisId);

        List<Temperatura> listaTemperaturas = pais.getTemperaturas();

        return ResponseEntity.ok(listaTemperaturas);

    }

    @PostMapping("/temperaturas")
    public ResponseEntity<GenericResponse> crearTemperatura(@RequestBody PostTemperaturaRequest tempReq) {

        Temperatura temperatura = temperaturaService.createTemperatura(tempReq.getPaisId(), tempReq.getAnio(), tempReq.getGrado());

        GenericResponse resp = new GenericResponse();
    
        resp.isOk = true;
        resp.message = "Se cargo la temperatura con exito";
        resp.id = temperatura.getTemperaturaId();
    
        return ResponseEntity.ok(resp);

    }

    @DeleteMapping("/temperaturas/{id}")
    public ResponseEntity<?> borrarTemperatura(@PathVariable Integer id) {

        GenericResponse genResp = new GenericResponse();

        Temperatura temperatura = temperaturaService.buscarPorId(id);

        if (temperatura != null) {

            temperatura.setAnio(0);
            genResp.isOk = true;
            genResp.id = temperatura.getTemperaturaId();
            genResp.message = "Temperatura borrada con exito";

            return ResponseEntity.ok(genResp);

        } else {

            genResp.isOk = false;
            genResp.message = "La temperatura no pudo ser borrada";

            return ResponseEntity.ok(genResp);

        }

    }

}