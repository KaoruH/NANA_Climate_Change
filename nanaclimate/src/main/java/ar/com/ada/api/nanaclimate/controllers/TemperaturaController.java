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
    public ResponseEntity<?> crearTemperatura(@RequestBody PostTemperaturaRequest tempReq) {

        Temperatura temperatura = new Temperatura();
        Pais pais = paisService.buscarPorId(tempReq.getPaisId());

        temperatura.setPais(pais);
        temperatura.setAnio(tempReq.getAnio());
        temperatura.setGrado(tempReq.getGrado());

        temperaturaService.crearTemperatura(temperatura);

        GenericResponse genResp = new GenericResponse();
        genResp.isOk = true;
        genResp.id = temperatura.getTemperaturaId();
        genResp.message = "Temperatura generada con exito";

        return ResponseEntity.ok(genResp);

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