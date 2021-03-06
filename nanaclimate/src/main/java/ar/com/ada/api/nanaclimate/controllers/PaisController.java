package ar.com.ada.api.nanaclimate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.nanaclimate.entities.Pais;
import ar.com.ada.api.nanaclimate.models.request.PostPaisRequest;
import ar.com.ada.api.nanaclimate.models.request.PutPaisRequest;
import ar.com.ada.api.nanaclimate.models.response.GenericResponse;
import ar.com.ada.api.nanaclimate.services.PaisService;

@RestController
public class PaisController {

    @Autowired
    PaisService paisService;

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> getPaises(@RequestParam(value = "nombre", required = false) String nombre) {

        List<Pais> listaPais = paisService.buscarTodos();

        return ResponseEntity.ok(listaPais);
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> getPaisById(@PathVariable Integer id) {

        Pais pais = paisService.buscarPorId(id);

        if (pais == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pais);
    }

    @PostMapping("/paises")
    public ResponseEntity<GenericResponse> postPais(@RequestBody PostPaisRequest pais) {

        paisService.crearPais(pais.getPaisId(), pais.getNombre());

        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.message = "Se agrego el pais " + pais.getNombre() + " con exito";
        resp.id = pais.getPaisId();

        return ResponseEntity.ok(resp);
    }


    @PutMapping("/paises/{id}")
    public ResponseEntity<?> putPais(@PathVariable Integer id, @RequestBody PutPaisRequest pPR) {

        GenericResponse genResp = new GenericResponse();

        Pais pais = paisService.buscarPorId(id);

        if (pais == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = paisService.actualizarNombre(pais, pPR.getNombre());

        if (resultado) {
            genResp.isOk = true;
            genResp.id = pais.getPaisId();
            genResp.message = "Nombre del país actualizado con éxito.";
            return ResponseEntity.ok(genResp);
        } else {

            genResp.isOk = false;
            genResp.message = "No se pudo actualizar el nombre del país.";

            return ResponseEntity.badRequest().body(genResp);
        }
    }

}