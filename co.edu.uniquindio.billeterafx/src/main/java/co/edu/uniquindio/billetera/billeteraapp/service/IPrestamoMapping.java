package co.edu.uniquindio.billetera.billeteraapp.service;

import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;

import java.util.List;

public interface IPrestamoMapping {
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
}
