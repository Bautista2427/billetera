package co.edu.uniquindio.billetera.billeteraapp.model.builder;

import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;

public class CuentaBuilder {
    protected String idCuenta;
    protected String nombreBanco;
    protected String numeroCuenta;
    protected String tipoCuenta;

    public CuentaBuilder idCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
        return this;
    }

    public CuentaBuilder nombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
        return this;
    }

    public CuentaBuilder numeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }

    public CuentaBuilder tipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
        return this;
    }

    public Cuenta build() {
        return new Cuenta(idCuenta, nombreBanco, numeroCuenta, tipoCuenta);
    }
}
