package co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

import co.edu.uniquindio.billetera.billeteraapp.controller.CuentaController;
import co.edu.uniquindio.billetera.billeteraapp.model.Cuenta;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;
import java.util.UUID;

import static co.edu.uniquindio.billetera.billeteraapp.utils.Constantes.*;

public class CuentasViewController {

    CuentaController cuentaController;
    ObservableList<Cuenta> listaCuentas = FXCollections.observableArrayList();
    Cuenta cuentaSeleccionada;

    @FXML
    private TextField txtIdCuenta;

    @FXML
    private TextField txtBanco;

    @FXML
    private TextField txtNumeroCuenta;

    @FXML
    private ComboBox<String> cmbTipoCuenta;

    @FXML
    private Button btnNueva;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Cuenta> tableCuentas;

    @FXML
    private TableColumn<Cuenta, String> tcIdCuenta;

    @FXML
    private TableColumn<Cuenta, String> tcBanco;

    @FXML
    private TableColumn<Cuenta, String> tcNumeroCuenta;

    @FXML
    private TableColumn<Cuenta, String> tcTipoCuenta;

    @FXML
    private TableColumn<Cuenta, Number> tcSaldo;

    @FXML
    void initialize() {
        cuentaController = new CuentaController();
        cmbTipoCuenta.getItems().addAll("Corriente", "Ahorros");
        initView();
    }

    @FXML
    void OnNueva(ActionEvent event) {
        nuevaCuenta();
    }

    @FXML
    void OnAgregar(ActionEvent event) {
        agregarCuenta();
    }

    @FXML
    void OnActualizar(ActionEvent event) {
        actualizarCuenta();
    }

    @FXML
    void OnEliminar(ActionEvent event) {
        eliminarCuenta();
    }

    private void initView() {
        initDataBinding();
        obtenerCuentas();
        tableCuentas.setItems(listaCuentas);
        listenerSelection();
    }

    private void obtenerCuentas() {
        listaCuentas.addAll(cuentaController.obtenerCuentas()); // <-- devuelve lista de Cuenta, no DTO
    }

    private void initDataBinding() {
        tcIdCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdCuenta()));
        tcBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreBanco()));
        tcNumeroCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumeroCuenta()));
        tcTipoCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoCuenta()));
        tcSaldo.setCellValueFactory(cellData -> cellData.getValue().saldoProperty()); // binding vivo
    }

    private void listenerSelection() {
        tableCuentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cuentaSeleccionada = newSelection;
            mostrarInformacionCuenta(cuentaSeleccionada);
        });
    }

    private void agregarCuenta() {
        Cuenta cuenta = crearCuenta();
        if (datosValidos(cuenta)) {
            if (cuentaController.agregarCuenta(cuenta)) {
                listaCuentas.add(cuenta);
                limpiarCampos();
                mostrarMensaje(TITULO_CUENTA_AGREGADO, HEADER, BODY_CUENTA_AGREGADO, Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje(TITULO_CUENTA_NO_AGREGADO, HEADER, BODY_CUENTA_NO_AGREGADO, Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO, Alert.AlertType.WARNING);
        }
    }

    private void actualizarCuenta() {
        if (cuentaSeleccionada != null) {
            Cuenta cuentaActualizada = crearCuenta();
            cuentaActualizada.setSaldo(cuentaSeleccionada.getSaldo()); // mantiene saldo actual
                if (cuentaController.actualizarCuenta(cuentaActualizada)) {
                int index = listaCuentas.indexOf(cuentaSeleccionada);
                listaCuentas.set(index, cuentaActualizada);
                limpiarCampos();
                mostrarMensaje("Cuenta actualizada", "", "La cuenta se actualizó correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Error al actualizar", "", "No se pudo actualizar la cuenta", Alert.AlertType.ERROR);
            }
        }
    }

    private void eliminarCuenta() {
        if (cuentaSeleccionada != null) {
            if (cuentaController.eliminarCuenta(cuentaSeleccionada.getIdCuenta())) {
                listaCuentas.remove(cuentaSeleccionada);
                limpiarCampos();
                mostrarMensaje(TITULO_CUENTA_ELIMINADO, HEADER, BODY_CUENTA_AGREGADO, Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje(TITULO_CUENTA_NO_AGREGADO, HEADER, BODY_CUENTA_NO_AGREGADO, Alert.AlertType.ERROR);
            }
        }
    }

    private void nuevaCuenta() {
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtIdCuenta.setText("");
        txtBanco.setText("");
        txtNumeroCuenta.setText("");
        cmbTipoCuenta.setValue(null);
    }

    private Cuenta crearCuenta() {
        String idCuentaGenerado = UUID.randomUUID().toString(); // genera un ID único

        Cuenta cuenta = new Cuenta(
                txtIdCuenta.getText(),
                txtBanco.getText(),
                txtNumeroCuenta.getText(),
                cmbTipoCuenta.getValue()
        );
        cuenta.setSaldo(0.0); // saldo inicial cero
        return cuenta;
    }

    private boolean datosValidos(Cuenta cuenta) {
        return !(cuenta.getIdCuenta().isBlank() ||
                cuenta.getNombreBanco().isBlank() ||
                cuenta.getNumeroCuenta().isBlank() ||
                cuenta.getTipoCuenta().isBlank());
    }

    private void mostrarInformacionCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            txtIdCuenta.setText(cuenta.getIdCuenta());
            txtBanco.setText(cuenta.getNombreBanco());
            txtNumeroCuenta.setText(cuenta.getNumeroCuenta());
            cmbTipoCuenta.setValue(cuenta.getTipoCuenta());
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }
}