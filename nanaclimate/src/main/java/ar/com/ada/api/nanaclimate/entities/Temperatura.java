package ar.com.ada.api.nanaclimate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "temperatura")
public class Temperatura {

    @Id
    @Column(name = "temperatura_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer temperaturaId;

    private Integer anio;

    private double grado;

    @ManyToOne
    @JoinColumn(name = "pais_id", referencedColumnName = "pais_id")
    private Pais pais;

    public Integer getTemperaturaId() {
        return temperaturaId;
    }

    public void setTemperaturaId(Integer temperaturaId) {
        this.temperaturaId = temperaturaId;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public double getGrado() {
        return grado;
    }

    public void setGrado(double grado) {
        this.grado = grado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
}