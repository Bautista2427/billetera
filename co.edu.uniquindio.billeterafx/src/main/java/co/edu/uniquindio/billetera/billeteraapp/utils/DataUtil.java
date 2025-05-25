package co.edu.uniquindio.billetera.billeteraapp.utils;

import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;
import co.edu.uniquindio.billetera.billeteraapp.model.GestorCuentas;
import co.edu.uniquindio.billetera.billeteraapp.model.GestorUsuarios;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.model.builder.UsuarioBuilder;
import co.edu.uniquindio.billetera.billeteraapp.model.builder.CuentaBuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataUtil {

    private static final List<Usuario> listaUsuarios = new ArrayList<>();
    private static final List<Cuenta> listaCuentas = new ArrayList<>();


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

        crearCuentaParaUsuario("2001", "Scotibank", "1232752", "Ahorros");
        crearCuentaParaUsuario("2001", "Bancolombia", "2752572", "Ahorros");
        crearCuentaParaUsuario("2002", "Nu Bannk", "1200275720", "Ahorros");
        crearCuentaParaUsuario("2003", "Nequi", "257275", "Ahorros");
        crearCuentaParaUsuario("2004", "Davivienda", "257227257", "Ahorros");
        crearCuentaParaUsuario("2005", "Estado", "572257272", "Ahorros");
    }

    private static void crearCuentaParaUsuario(String idCuenta, String nombreBanco, String numeroCuenta, String tipoCuenta) {
        String idCuentaGenerado = UUID.randomUUID().toString();

        Cuenta cuenta = new CuentaBuilder()
                .idCuenta(idCuenta)
                .nombreBanco(nombreBanco)
                .numeroCuenta(numeroCuenta)
                .tipoCuenta(tipoCuenta)
                .build();

        listaCuentas.add(cuenta);

        Usuario usuario = listaUsuarios.stream()
                .filter(u -> u.getCedula().equals(idCuenta))
                .findFirst()
                .orElse(null);

        if (usuario != null) {
            usuario.getlistaCuentas().add(cuenta);
        }
    }

    public static Usuario validarCredenciales(String cedula, String contrasena) {
        for (Usuario u : listaUsuarios) {
            if (u.getCedula().equals(cedula) && u.getContrasena().equals(contrasena)) {
                return u;
            }
        }
        return null;
    }


    public static GestorUsuarios inicializarDatos() {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        for (Usuario usuario : listaUsuarios) {
            gestorUsuarios.crearUsuario(usuario);
        }
        return gestorUsuarios;
    }

    public static GestorCuentas inicializarDatosCuentas() {
        GestorCuentas gestorCuentas = new GestorCuentas();
        for (Cuenta cuenta : listaCuentas) {
            gestorCuentas.crearCuenta(cuenta);
        }
        return gestorCuentas;
    }
}