package co.edu.uniquindio.billetera.billeteraapp.model;

public class Cuenta {
    private String idCuenta;
    private String nombreBanco;
    private String correoCuenta;
    private String tipoCuenta;

    public Cuenta(String idCuenta,
                  String nombreBanco,
                  String correoCuenta,
                  String tipoCuenta) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.correoCuenta = correoCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getCorreoCuenta() {
        return correoCuenta;
    }

    public void setCorreoCuenta(String correoCuenta) {
        this.correoCuenta = correoCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}
