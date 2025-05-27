package co.edu.uniquindio.billetera.billeteraapp.controller;

import co.edu.uniquindio.billetera.billeteraapp.factory.ModelFactory;
import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;

import java.util.List;

public class CuentaController {
    ModelFactory modelFactory;

    public CuentaController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Cuenta> obtenerCuentas() {
        return modelFactory.obtenerCuentas();
    }

    public boolean agregarCuenta(Cuenta cuenta) {
        return modelFactory.agregarCuenta(cuenta);
    }

    public boolean actualizarCuenta(Cuenta cuenta) {
        return modelFactory.actualizarCuenta(cuenta);
    }

    public boolean eliminarCuenta(String idCuenta) {
        return modelFactory.eliminarCuenta(idCuenta);
    }
}