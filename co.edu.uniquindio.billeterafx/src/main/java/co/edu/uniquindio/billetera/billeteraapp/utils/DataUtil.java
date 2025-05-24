package co.edu.uniquindio.billetera.billeteraapp.utils;

import co.edu.uniquindio.billetera.billeteraapp.model.PrestamoObjeto;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.model.builder.UsuarioBuilder;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    private static final List<Usuario> listaUsuarios = new ArrayList<>();

    static {
        listaUsuarios.add(new UsuarioBuilder()
                .cedula("1001")
                .contrasena("admin123")
                .esAdmin(true)
                .nombreCompleto("Administrador Uno")
                .correoElectronico("admin1@billetera.com")
                .saldo("100000")
                .build());

        listaUsuarios.add(new UsuarioBuilder()
                .cedula("1002")
                .contrasena("admin456")
                .esAdmin(true)
                .nombreCompleto("Administrador Dos")
                .correoElectronico("admin2@billetera.com")
                .saldo("90000")
                .build());


        listaUsuarios.add(new UsuarioBuilder()
                .cedula("2001")
                .contrasena("user123")
                .esAdmin(false)
                .nombreCompleto("Juan Pérez")
                .correoElectronico("juanp@gmail.com")
                .saldo("30000")
                .build());

        listaUsuarios.add(new UsuarioBuilder()
                .cedula("2002")
                .contrasena("clave456")
                .esAdmin(false)
                .nombreCompleto("Ana López")
                .saldo("25000")
                .build());

        listaUsuarios.add(new UsuarioBuilder()
                .cedula("2003")
                .contrasena("pass789")
                .esAdmin(false)
                .nombreCompleto("Carlos Díaz")
                .correoElectronico("carlos.diaz@mail.com")
                .saldo("50000")
                .build());

        listaUsuarios.add(new UsuarioBuilder()
                .cedula("2004")
                .contrasena("micontra")
                .esAdmin(false)
                .nombreCompleto("María Ruiz")
                .build());

        listaUsuarios.add(new UsuarioBuilder()
                .cedula("2005")
                .contrasena("segura999")
                .esAdmin(false)
                .nombreCompleto("Luis Gómez")
                .build());
    }

    public static Usuario validarCredenciales(String cedula, String contrasena) {
        for (Usuario u : listaUsuarios) {
            if (u.getCedula().equals(cedula) && u.getContrasena().equals(contrasena)) {
                return u;
            }
        }
        return null;
    }

    public static List<Usuario> getUsuarios() {
        return listaUsuarios;
    }

    public static PrestamoObjeto inicializarDatos() {
        PrestamoObjeto prestamoObjeto = new PrestamoObjeto();
        for (Usuario usuario : listaUsuarios) {
            prestamoObjeto.crearUsuario(usuario);
        }
        return prestamoObjeto;
    }
}
