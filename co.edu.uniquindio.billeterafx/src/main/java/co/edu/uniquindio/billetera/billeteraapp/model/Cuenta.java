package co.edu.uniquindio.billetera.billeteraapp.model;

import co.edu.uniquindio.billetera.billeteraapp.model.builder.CuentaBuilder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Cuenta {

    private String idCuenta;
    private String nombreBanco;
    private String numeroCuenta;
    private String tipoCuenta;

    private DoubleProperty saldo = new SimpleDoubleProperty(0.0);

    public Cuenta(String idCuenta,
                  String nombreBanco,
                  String numeroCuenta,
                  String tipoCuenta) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = new SimpleDoubleProperty(0.0);
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

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo.get();
    }

    public void setSaldo(double saldo) {
        this.saldo.set(saldo);
    }

    public DoubleProperty saldoProperty() {
        return saldo;
    }


    public static CuentaBuilder builder() {
        return new CuentaBuilder();
    }

    @Override
    public String toString() {
        return nombreBanco + " - " + numeroCuenta;
    }
}