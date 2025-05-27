package co.edu.uniquindio.billetera.billeteraapp.service;

import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;
import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;

import java.util.List;

public interface IModelFactoryService {
    List<UsuarioDto> obtenerUsuarios();

    boolean agregarUsuario(UsuarioDto usuarioDto);

    boolean actualizarUsuario(UsuarioDto usuarioDto);

    boolean eliminarUsuario(String cedula);


    List<Cuenta> obtenerCuentas();

    boolean agregarCuenta(Cuenta cuenta);

    boolean actualizarCuenta(Cuenta cuenta);

    boolean eliminarCuenta(String idCuenta);
}