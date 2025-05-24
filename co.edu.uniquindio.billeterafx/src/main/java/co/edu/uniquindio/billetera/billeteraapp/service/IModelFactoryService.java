package co.edu.uniquindio.billetera.billeteraapp.service;

import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;

import java.util.List;

public interface IModelFactoryService {
    List<UsuarioDto> obtenerUsuarios();
}