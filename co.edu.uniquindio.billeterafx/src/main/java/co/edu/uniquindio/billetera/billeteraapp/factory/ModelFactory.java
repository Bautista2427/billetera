package co.edu.uniquindio.billetera.billeteraapp.factory;

import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billetera.billeteraapp.mapping.mappers.MappingImpl;
import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.model.GestorUsuarios;
import co.edu.uniquindio.billetera.billeteraapp.model.GestorCuentas;
import co.edu.uniquindio.billetera.billeteraapp.service.IMapping;
import co.edu.uniquindio.billetera.billeteraapp.service.IModelFactoryService;
import co.edu.uniquindio.billetera.billeteraapp.service.Observer;
import co.edu.uniquindio.billetera.billeteraapp.service.Subject;
import co.edu.uniquindio.billetera.billeteraapp.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class ModelFactory implements IModelFactoryService, Subject {
    private static ModelFactory modelFactory;
    private GestorUsuarios gestorUsuarios;
    private GestorCuentas gestorCuentas;
    private Usuario usuarioActual;

    private List<Observer> observers = new ArrayList<>();

    private IMapping mapper;

    public static ModelFactory getInstancia() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory() {
        gestorUsuarios = DataUtil.inicializarDatos();
        gestorCuentas = DataUtil.inicializarDatosCuentas();
        this.mapper = new MappingImpl();
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

    public List<Cuenta> obtenerCuentas() {
        if (usuarioActual == null) {
            return new ArrayList<>();
        }

        if (usuarioActual.isEsAdmin()) {
            return gestorCuentas.getListaCuentas();
        }

        return usuarioActual.getListaCuentas();
    }

    public boolean agregarCuenta(Cuenta cuenta) {
        boolean creada = gestorCuentas.crearCuenta(cuenta);
        if (creada) {
            if (usuarioActual != null) {
                usuarioActual.getListaCuentas().add(cuenta);
                notificarObservers();
            }
        }
        return creada;
    }

    public boolean actualizarCuenta(Cuenta cuentaActualizada) {
        List<Cuenta> listaCuentas = gestorCuentas.getListaCuentas();
        for (int i = 0; i < listaCuentas.size(); i++) {
            if (listaCuentas.get(i).getIdCuenta().equals(cuentaActualizada.getIdCuenta())) {
                listaCuentas.set(i, cuentaActualizada);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCuenta(String idCuenta) {
        boolean eliminada = gestorCuentas.eliminarCuenta(idCuenta);
        if (eliminada && usuarioActual != null) {
            usuarioActual.getListaCuentas().removeIf(cuenta -> cuenta.getIdCuenta().equals(idCuenta));
            notificarObservers();
        }
        return eliminada;
    }


    public GestorUsuarios getGestorUsuarios() {
        return gestorUsuarios;
    }

    @Override
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void eliminarObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers() {
        for (Observer observer : observers) {
            observer.actualizar();
        }
    }
}