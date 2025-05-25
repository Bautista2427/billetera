package co.edu.uniquindio.billetera.billeteraapp.model;

import java.util.ArrayList;
import java.util.List;

public class GestorCuentas {
    List<Cuenta> listaCuentas = new ArrayList<>();

    private String nombre;

    public GestorCuentas() {
    }

    public boolean crearCuenta(String idCuenta,
                               String nombreBanco,
                               String numeroCuenta,
                               String tipoCuenta){
        Cuenta cuentaEncontrada = obtenerCuenta(idCuenta);
        if(cuentaEncontrada == null){
            Cuenta cuenta = getBuildCuenta(idCuenta, nombreBanco, numeroCuenta, tipoCuenta);
            getListaCuentas().add(cuenta);
            return true;
        }else{
            return  false;
        }
    }

    public boolean crearCuenta(Cuenta nuevaCuenta){
        Cuenta cuentaEncontrado = obtenerCuenta(nuevaCuenta.getIdCuenta());
        if(cuentaEncontrado == null){
            getListaCuentas().add(nuevaCuenta);
            return true;
        }else{
            return  false;
        }
    }

    private Cuenta getBuildCuenta(String idCuenta, String nombreBanco, String numeroCuenta, String tipoCuenta) {
        return Cuenta.builder()
                .idCuenta(idCuenta)
                .nombreBanco(nombreBanco)
                .numeroCuenta(numeroCuenta)
                .tipoCuenta(tipoCuenta)
                .build();
    }

    private Cuenta obtenerCuenta(String idCuenta) {
        Cuenta cuenta = null;
        for (Cuenta cuenta1: getListaCuentas()) {
            if(cuenta1.getIdCuenta().equalsIgnoreCase(idCuenta)){
                cuenta = cuenta1;
                break;
            }
        }

        return cuenta;
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean eliminarCuenta(String idCuenta) {
        Cuenta cuentaEncontrada = obtenerCuenta(idCuenta);
        if(cuentaEncontrada !=null){
            getListaCuentas().remove(cuentaEncontrada);
            return true;
        }else{
            return false;
        }
    }
}