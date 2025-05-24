package co.edu.uniquindio.billetera.billeteraapp.mapping.mappers;

import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.service.IPrestamoMapping;

import java.util.ArrayList;
import java.util.List;

public class PrestamoMappingImpl implements IPrestamoMapping {


    @Override
    public List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios) {
        if(listaUsuarios == null){
            return null;
        }
        List<UsuarioDto> listaUsuariosDto = new ArrayList<UsuarioDto>(listaUsuarios.size());
        for (Usuario usuario : listaUsuarios) {
            listaUsuariosDto.add(usuarioToUsuarioDto(usuario));
        }

        return listaUsuariosDto;
    }

    @Override
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getCedula(),
                usuario.getContrasena(),
                usuario.esAdmin(),
                usuario.getNombreCompleto(),
                usuario.getCorreoElectronico(),
                usuario.getNumeroTelefono(),
                usuario.getDireccion(),
                usuario.getSaldo());
    }

    @Override
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
        return Usuario.builder()
                .cedula(usuarioDto.cedula())
                .contrasena(usuarioDto.contrasena())
                .esAdmin(usuarioDto.esAdmin())
                .nombreCompleto(usuarioDto.nombreCompleto())
                .correoElectronico(usuarioDto.correoElectronico())
                .numeroTelefono(usuarioDto.numeroTelefono())
                .direccion(usuarioDto.direccion())
                .saldo(usuarioDto.saldo())
                .build();
    }
}
