package co.edu.uniquindio.billetera.billeteraapp.mapping.dto;

public record CuentaDto(
        String idCuenta,
        String nombreBanco,
        String numeroCuenta,
        String tipoCuenta
        //String Saldo
) {
}