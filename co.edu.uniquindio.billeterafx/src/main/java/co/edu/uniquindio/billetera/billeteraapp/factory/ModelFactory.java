package co.edu.uniquindio.billetera.billeteraapp.factory;

import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billetera.billeteraapp.mapping.mappers.MappingImpl;
import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.model.GestorUsuarios;
import co.edu.uniquindio.billetera.billeteraapp.model.GestorCuentas;
import co.edu.uniquindio.billetera.billeteraapp.service.IModelFactoryService;
import co.edu.uniquindio.billetera.billeteraapp.service.IMapping;
import co.edu.uniquindio.billetera.billeteraapp.utils.DataUtil;

import java.util.List;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private IMapping mapper;
    private GestorUsuarios gestorUsuarios;
    private GestorCuentas gestorCuentas;
    private Usuario usuarioActual;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        mapper = new MappingImpl();
        gestorUsuarios = DataUtil.inicializarDatos();
        gestorCuentas = DataUtil.inicializarDatosCuentas();
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return mapper.getUsuariosDto(gestorUsuarios.getListaUsuarios());
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
        return gestorUsuarios.crearUsuario(usuario);
        //return gestorUsuarios.crearUsuario(mapper.usuarioDtoToUsuario(usuarioDto));
    }

    public boolean actualizarUsuario(UsuarioDto usuarioDto) {
        List<Usuario> listaUsuarios = gestorUsuarios.getListaUsuarios();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getCedula().equals(usuarioDto.cedula())) {
                Usuario usuarioActualizado = mapper.usuarioDtoToUsuario(usuarioDto);
                listaUsuarios.set(i, usuarioActualizado);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(String cedula) {
        return gestorUsuarios.eliminarUsuario(cedula);
    }




    @Override
    public List<CuentaDto> obtenerCuentas() {
        if (usuarioActual == null) {
            return List.of();
        }

        if (usuarioActual.isEsAdmin()) {
            return mapper.getCuentasDto(gestorCuentas.getListaCuentas());
        }

        return mapper.getCuentasDto(usuarioActual.getListaCuentas());
    }

    @Override
    public boolean agregarCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = mapper.cuentaDtoToCuenta(cuentaDto);
        return gestorCuentas.crearCuenta(cuenta);
        //return gestorCuentas.crearCuenta(mapper.cuentaDtoToUsuario(cuentaDto));
    }

    public boolean actualizarCuenta(CuentaDto cuentaDto) {
        List<Cuenta> listaCuentas = gestorCuentas.getListaCuentas();
        for (int i = 0; i < listaCuentas.size(); i++) {
            if (listaCuentas.get(i).getIdCuenta().equals(cuentaDto.idCuenta())) {
                Cuenta cuentaActualizada = mapper.cuentaDtoToCuenta(cuentaDto);
                listaCuentas.set(i, cuentaActualizada);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCuenta(String idCuenta) {
        return gestorCuentas.eliminarCuenta(idCuenta);
    }

    public IMapping getMapper() {
        return mapper;
    }

    public GestorUsuarios getGestorUsuarios() {
        return gestorUsuarios;
    }
}