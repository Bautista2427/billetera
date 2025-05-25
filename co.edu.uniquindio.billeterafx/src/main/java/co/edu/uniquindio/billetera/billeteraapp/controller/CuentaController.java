package co.edu.uniquindio.billetera.billeteraapp.controller;

import co.edu.uniquindio.billetera.billeteraapp.factory.ModelFactory;
import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.CuentaDto;

import java.util.List;

public class CuentaController {
    ModelFactory modelFactory;
    public CuentaController() { modelFactory = ModelFactory.getInstancia();}

    public List<CuentaDto> obtenerCuentas() {
        return modelFactory.obtenerCuentas();
    }

    public boolean agregarCuenta(CuentaDto cuentaDto) {
        return modelFactory.agregarCuenta(cuentaDto);
    }

    public boolean actualizarCuenta(CuentaDto cuentaDto) {
        return modelFactory.actualizarCuenta(cuentaDto);
    }

    public boolean eliminarCuenta(String idCuenta) {
        return modelFactory.eliminarCuenta(idCuenta);
    }
}