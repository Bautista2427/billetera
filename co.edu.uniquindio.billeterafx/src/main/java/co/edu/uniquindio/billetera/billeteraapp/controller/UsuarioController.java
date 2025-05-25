package co.edu.uniquindio.billetera.billeteraapp.controller;

import co.edu.uniquindio.billetera.billeteraapp.factory.ModelFactory;
import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioController {
    ModelFactory modelFactory;
    public UsuarioController(){
        modelFactory = ModelFactory.getInstancia();
    }

    public List<UsuarioDto> obtenerUsuarios() {
        return modelFactory.obtenerUsuarios();
    }

    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        return modelFactory.agregarUsuario(usuarioDto);
    }

    public boolean actualizarUsuario(UsuarioDto usuarioDto) {
        return modelFactory.actualizarUsuario(usuarioDto);
    }

    public boolean eliminarUsuario(String cedula) {
        return modelFactory.eliminarUsuario(cedula);
    }
}