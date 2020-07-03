package ar.com.ada.api.nanaclimate.models.request;

public class PostTemperaturaRequest {

    private Integer paisId;
    private Integer anio;
    private double grado;

    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
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
    
}