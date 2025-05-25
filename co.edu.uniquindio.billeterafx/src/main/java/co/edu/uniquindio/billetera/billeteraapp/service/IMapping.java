package co.edu.uniquindio.billetera.billeteraapp.service;

import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;

import java.util.List;

public interface IMapping {
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    List<CuentaDto> getCuentasDto(List<Cuenta> listaCuentas);
    CuentaDto cuentaToCuentaDto(Cuenta cuenta);
    Cuenta cuentaDtoToCuenta(CuentaDto cuentaDto);
}
