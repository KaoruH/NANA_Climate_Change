package ar.com.ada.api.nanaclimate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.nanaclimate.entities.Pais;
import ar.com.ada.api.nanaclimate.models.request.PutPaisRequest;
import ar.com.ada.api.nanaclimate.models.response.GenericResponse;
import ar.com.ada.api.nanaclimate.services.PaisService;

@RestController
public class PaisController {

    @Autowired
    PaisService paisService;

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> getPaises(@RequestParam(value = "nombre", required = false) String nombre){

        List<Pais> listaPais = paisService.buscarTodos();

        return ResponseEntity.ok(listaPais);
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> getPaisById(@PathVariable Integer id){

        Pais pais = paisService.buscarPorId(id);

        if (pais == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pais);
    }

    @PostMapping("paises/")
    public ResponseEntity<?> postPais(@RequestBody Pais paisReq){

        GenericResponse genResp = new GenericResponse();

        boolean resultado = paisService.crearPais(paisReq);

        if (resultado) {
            genResp.isOk = true;
            genResp.id = paisReq.getPaisId();
            genResp.message = "Creaste un pais con éxito.";
            return ResponseEntity.ok(genResp);
        } else {

            genResp.isOk = false;
            genResp.message = "No se pudo crear el pais! Este pais ya existe.";

            return ResponseEntity.badRequest().body(genResp);
        }
    }

    @PutMapping("paises/{id}")
    public ResponseEntity<?> putPais(@PathVariable Integer id, @RequestBody PutPaisRequest pPR){

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