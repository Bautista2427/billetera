package co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

import co.edu.uniquindio.billetera.billeteraapp.factory.ModelFactory;
import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;
import co.edu.uniquindio.billetera.billeteraapp.model.Transaccion;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.service.Observer; // ⭐ importamos Observer
import javafx.application.Platform; // ⭐ para trabajar seguro en el hilo de UI
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.UUID;

public class TransaccionesViewController implements Observer {

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtMonto;

    @FXML
    private TextArea txtMensaje;

    @FXML
    private ComboBox<Cuenta> cbCuentaOrigen;

    @FXML
    private ComboBox<Cuenta> cbCuentaDestino;

    @FXML
    private Button btnRealizarTransaccion;

    private Usuario usuarioActual;
    private ModelFactory modelFactory;

    @FXML
    public void initialize() {
        txtId.setText(UUID.randomUUID().toString());
        txtFecha.setText(LocalDate.now().toString());

        modelFactory = ModelFactory.getInstancia(); // ⭐
        modelFactory.agregarObserver(this); // ⭐ nos registramos como Observer

        usuarioActual = ModelFactory.getInstancia().getUsuarioActual();

        if (usuarioActual != null) {
            cargarCuentas();
        } else {
            txtMensaje.setText("⚠ No se encontró usuario activo en la sesión.");
        }
    }

    private void cargarCuentas() {
        cbCuentaOrigen.setItems(FXCollections.observableArrayList(modelFactory.obtenerCuentas()));
        cbCuentaDestino.setItems(FXCollections.observableArrayList(modelFactory.obtenerCuentas()));

        cbCuentaOrigen.setConverter(new StringConverter<>() {
            @Override
            public String toString(Cuenta cuenta) {
                return cuenta != null ? cuenta.getNumeroCuenta() + " (" + cuenta.getSaldo() + ")" : "";
            }

            @Override
            public Cuenta fromString(String s) {
                return null;
            }
        });

        cbCuentaDestino.setConverter(cbCuentaOrigen.getConverter());
    }

    @FXML
    void OnRealizarTransaccion(ActionEvent event) {
        try {
            String tipo = txtTipo.getText();
            double monto = Double.parseDouble(txtMonto.getText());
            Cuenta cuentaOrigen = cbCuentaOrigen.getValue();
            Cuenta cuentaDestino = cbCuentaDestino.getValue();

            if (cuentaOrigen == null || cuentaDestino == null) {
                txtMensaje.setText("Seleccione las cuentas origen y destino.");
                return;
            }

            if (cuentaOrigen == cuentaDestino) {
                txtMensaje.setText("La cuenta origen y destino no pueden ser la misma.");
                return;
            }

            if (monto <= 0) {
                txtMensaje.setText("El monto debe ser mayor a cero.");
                return;
            }

            if (cuentaOrigen.getSaldo() < monto) {
                txtMensaje.setText("Saldo insuficiente en la cuenta origen.");
                return;
            }

            // Actualiza saldos (si usas DoubleProperty, la tabla se refresca sola)
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

            // Crear transacción (opcional: guarda en alguna lista si quieres llevar historial)
            Transaccion transaccion = new Transaccion(
                    txtId.getText(),
                    txtFecha.getText(),
                    tipo,
                    monto,
                    cuentaOrigen,
                    cuentaDestino
            );

            txtMensaje.setText("✅ Transacción realizada exitosamente:\n" + transaccion);

            // Limpia campos y genera nuevo ID y fecha
            limpiarCampos();

        } catch (NumberFormatException e) {
            txtMensaje.setText("Ingrese un monto válido.");
        } catch (Exception e) {
            txtMensaje.setText("Error al realizar transacción: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtId.setText(UUID.randomUUID().toString());
        txtFecha.setText(LocalDate.now().toString());
        txtTipo.clear();
        txtMonto.clear();
    }

    @Override
    public void actualizar() { // ⭐ método del Observer
        Platform.runLater(() -> {
            cargarCuentas();
            txtMensaje.setText("🔄 Se han actualizado las cuentas.");
        });
    }

    // ⭐ (opcional) Si tienes un método para cerrar o destruir el controlador:
    public void cerrar() {
        modelFactory.eliminarObserver(this);
    }
}