package co.edu.uniquindio.billetera.billeteraapp.model;

public class Presupuesto {
    private String idPresupuesto;
    private String nombre;
    private String montoAsignado;
    private String montoGastado;

    public Presupuesto(String idPresupuesto,
                       String nombre,
                       String montoAsignado,
                       String montoGastado) {
        this.idPresupuesto = idPresupuesto;
        this.nombre = nombre;
        this.montoAsignado = montoAsignado;
        this.montoGastado = montoGastado;
    }

    public String getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(String idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(String montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public String getMontoGastado() {
        return montoGastado;
    }

    public void setMontoGastado(String montoGastado) {
        this.montoGastado = montoGastado;
    }
}
