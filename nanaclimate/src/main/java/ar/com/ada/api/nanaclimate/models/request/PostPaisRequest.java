package ar.com.ada.api.nanaclimate.models.request;

public class PostPaisRequest {

    private Integer paisId;
    private String nombre;

    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}