package co.edu.uniquindio.billetera.billeteraapp.model.builder;

import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;

public class UsuarioBuilder {
    protected String cedula;
    protected String contrasena;
    protected boolean esAdmin;
    protected String nombreCompleto;
    protected String correoElectronico;
    protected String numeroTelefono;
    protected String direccion;
    protected String saldo;

    public UsuarioBuilder cedula(String cedula) {
        this.cedula = cedula;
        return this;
    }

    public UsuarioBuilder contrasena(String contrasena) {
        this.contrasena = contrasena;
        return this;
    }

    public UsuarioBuilder esAdmin(Boolean esAdmin) {
        this.esAdmin = esAdmin;
        return this;
    }

    public UsuarioBuilder nombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        return this;
    }

    public UsuarioBuilder correoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public UsuarioBuilder numeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
        return this;
    }

    public UsuarioBuilder direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public UsuarioBuilder saldo(String saldo) {
        this.saldo = saldo;
        return this;
    }

    public Usuario build() {
        return new Usuario(cedula, contrasena, esAdmin, nombreCompleto, correoElectronico, numeroTelefono, direccion, saldo);
    }
}
