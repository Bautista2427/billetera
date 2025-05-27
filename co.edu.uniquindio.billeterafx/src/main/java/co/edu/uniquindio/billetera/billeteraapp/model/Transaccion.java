package co.edu.uniquindio.billetera.billeteraapp.model;

public class Transaccion {
    private String idTransaccion;
    private String fecha;
    private String tipoTransaccion;
    private double monto;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public Transaccion(String idTransaccion, String fecha, String tipoTransaccion, double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    @Override
    public String toString() {
        return tipoTransaccion + " de $" + monto + " de " + cuentaOrigen.getNombreBanco() + " a " + cuentaDestino.getNombreBanco();
    }
}