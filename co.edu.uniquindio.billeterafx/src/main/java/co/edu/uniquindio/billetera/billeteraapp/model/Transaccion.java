package co.edu.uniquindio.billetera.billeteraapp.model;

public class Transaccion {
    private String idTransaccion;
    private String fecha;
    private String tipoTransaccion;
    private String monto;
    private String descripcion;
    private String cuentaAsociada;

    public Transaccion(String idTransaccion,
                       String fecha,
                       String tipoTransaccion,
                       String monto,
                       String descripcion,
                       String cuentaAsociada) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
        this.cuentaAsociada = cuentaAsociada;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(String cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }
}
