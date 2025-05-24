package co.edu.uniquindio.billetera.billeteraapp.service;

import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;

import java.util.List;

public interface IPrestamoUQ {
    boolean crearUsuario(String nombre, String apellido, String cedula, int edad);
    void eliminarUsuario(String cedula);
    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuario(String cedula);
    void mostrarInformacionUsuarios();
    void buscarUsuario(String cedula);
    boolean actualizarUsuario(String cedulaActual, String nombre, String apellido, String cedula, int edad);
}