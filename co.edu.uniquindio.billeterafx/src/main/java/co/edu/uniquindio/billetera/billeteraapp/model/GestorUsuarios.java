package co.edu.uniquindio.billetera.billeteraapp.model;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    List<Usuario> listaUsuarios = new ArrayList<>();

    private String nombre;


    public GestorUsuarios() {
    }

    public boolean crearUsuario(String cedula,
                                String contrasena,
                                boolean esAdmin,
                                String nombreCompleto,
                                String correoElectronico,
                                String numeroTelefono,
                                String direccion,
                                String saldo){
        Usuario usuarioEncontrado = obtenerUsuario(cedula);
        if(usuarioEncontrado == null){
            Usuario usuario = getBuildUsuario(cedula, contrasena, esAdmin, nombreCompleto, correoElectronico, numeroTelefono, direccion, saldo);
            getListaUsuarios().add(usuario);
            return true;
        }else{
            return  false;
        }
    }

    public boolean crearUsuario(Usuario nuevoUsuario){
        Usuario usuarioEncontrado = obtenerUsuario(nuevoUsuario.getCedula());
        if(usuarioEncontrado == null){
            getListaUsuarios().add(nuevoUsuario);
            return true;
        }else{
            return  false;
        }
    }

    private Usuario getBuildUsuario(String cedula, String contrasena, boolean esAdmin, String nombreCompleto, String correoElectronico, String numeroTelefono, String direccion, String saldo) {
        return Usuario.builder()
                .cedula(cedula)
                .contrasena(contrasena)
                .esAdmin(esAdmin)
                .nombreCompleto(nombreCompleto)
                .correoElectronico(correoElectronico)
                .numeroTelefono(numeroTelefono)
                .direccion(direccion)
                .saldo(saldo)
                .build();
    }

    private Usuario obtenerUsuario(String cedula) {
        Usuario usuario = null;
        for (Usuario usuario1: getListaUsuarios()) {
            if(usuario1.getCedula().equalsIgnoreCase(cedula)){
                usuario = usuario1;
                break;
            }
        }

        return usuario;
    }


    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public boolean eliminarUsuario(String cedula) {
        Usuario usuarioEncontrado = obtenerUsuario(cedula);
        if(usuarioEncontrado !=null){
            getListaUsuarios().remove(usuarioEncontrado);
            return true;
        }else{
            return false;
        }
    }

    public Usuario obtenerUsuarioPorCredenciales(String cedula, String contrasena) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCedula().equals(cedula) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }
}