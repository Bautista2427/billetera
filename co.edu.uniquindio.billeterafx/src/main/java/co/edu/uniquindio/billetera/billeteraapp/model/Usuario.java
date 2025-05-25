package co.edu.uniquindio.billetera.billeteraapp.model;

import co.edu.uniquindio.billetera.billeteraapp.model.builder.UsuarioBuilder;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String cedula;
    private String contrasena;
    private boolean esAdmin;
    private String nombreCompleto;
    private String correoElectronico;
    private String numeroTelefono;
    private String direccion;
    private String saldo;

    private List<Cuenta> listaCuentas;

    public Usuario(String cedula,
                   String contrasena,
                   boolean esAdmin,
                   String nombreCompleto,
                   String correoElectronico,
                   String numeroTelefono,
                   String direccion,
                   String saldo) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.esAdmin = esAdmin;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.saldo = saldo;
        this.listaCuentas = new ArrayList<>();
    }

    public List<Cuenta> getlistaCuentas() {
        return listaCuentas;
    }

    public void setlistaCuentas(List<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public boolean esAdmin() {
        return esAdmin;
    }

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    public List<Cuenta> getListaCuentas() {
        return listaCuentas;
    }
}