package co.edu.uniquindio.billetera.billeteraapp.service;

import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;

import java.util.List;

public interface IModelFactoryService {
    List<UsuarioDto> obtenerUsuarios();

    boolean agregarUsuario(UsuarioDto usuarioDto);

    boolean actualizarUsuario(UsuarioDto usuarioDto);

    boolean eliminarUsuario(String cedula);


    List<CuentaDto> obtenerCuentas();

    boolean agregarCuenta(CuentaDto cuentaDto);

    boolean actualizarCuenta(CuentaDto cuentaDto);

    boolean eliminarCuenta(String idCuenta);
}