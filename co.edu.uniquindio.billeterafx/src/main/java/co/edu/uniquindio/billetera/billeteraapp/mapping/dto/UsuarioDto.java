package co.edu.uniquindio.billetera.billeteraapp.mapping.dto;

public record UsuarioDto(
        String cedula,
        String contrasena,
        boolean esAdmin,
        String nombreCompleto,
        String correoElectronico,
        String numeroTelefono,
        String direccion,
        String saldo
) {
}