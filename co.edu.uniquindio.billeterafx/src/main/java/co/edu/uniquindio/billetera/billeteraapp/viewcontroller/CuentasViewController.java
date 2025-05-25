package co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

import co.edu.uniquindio.billetera.billeteraapp.controller.CuentaController;
import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.CuentaDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

import static co.edu.uniquindio.billetera.billeteraapp.utils.Constantes.*;

public class CuentasViewController {

    CuentaController cuentaController;
    ObservableList<CuentaDto> listaCuentas = FXCollections.observableArrayList();
    CuentaDto cuentaSeleccionada;

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
    private javafx.scene.control.TableView<CuentaDto> tableCuentas;

    @FXML
    private TableColumn<CuentaDto, String> tcIdCuenta;

    @FXML
    private TableColumn<CuentaDto, String> tcBanco;

    @FXML
    private TableColumn<CuentaDto, String> tcNumeroCuenta;

    @FXML
    private TableColumn<CuentaDto, String> tcTipoCuenta;

    @FXML
    void OnTipoCuenta(ActionEvent event) {
        String metodo = cmbTipoCuenta.getValue();
        System.out.println(metodo);

    }
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
        tableCuentas.getItems().clear();
        tableCuentas.setItems(listaCuentas);
        listenerSelection();
    }

    private void obtenerCuentas() {
        listaCuentas.addAll(cuentaController.obtenerCuentas());
    }

    private void initDataBinding() {
        tcIdCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().idCuenta()));
        tcBanco.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreBanco()));
        tcNumeroCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numeroCuenta()));
        tcTipoCuenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().tipoCuenta()));
    }

    private void listenerSelection() {
        tableCuentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            cuentaSeleccionada = newSelection;
            mostrarInformacionUsuario(cuentaSeleccionada);
        });
    }

    private void agregarCuenta() {
        //1. Captura los datos del formulario
        //2. Armar un Dto con los datos
        CuentaDto cuentaDto = crearCuentaDto();
        //3.Validar campos
        if(datosValidos(cuentaDto)){
            //4. Solicitar crear cuenta
            if(cuentaController.agregarCuenta(cuentaDto)){
                listaCuentas.add(cuentaDto);
                limpiarCampos();
                mostrarMensaje(TITULO_CUENTA_AGREGADO, HEADER, BODY_CUENTA_AGREGADO, Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_CUENTA_NO_AGREGADO, HEADER, BODY_CUENTA_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }else{
            //mensaje de notificacion de campos incompletos
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
        }
    }

    private void actualizarCuenta() {
        if (cuentaSeleccionada != null) {
            CuentaDto cuentaDto = crearCuentaDto();
            if (cuentaController.actualizarCuenta(cuentaDto)) {
                int index = listaCuentas.indexOf(cuentaSeleccionada);
                listaCuentas.set(index, cuentaDto);
                limpiarCampos();
                mostrarMensaje("Cuenta actualizada", "", "La cuenta se actualizó correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Error al actualizar", "", "No se pudo actualizar la cuenta", Alert.AlertType.ERROR);
            }
        }
    }

    private void eliminarCuenta() {
        if(cuentaSeleccionada != null){
            if(cuentaController.eliminarCuenta(cuentaSeleccionada.idCuenta())){
                listaCuentas.remove(cuentaSeleccionada);
                limpiarCampos();
                mostrarMensaje(TITULO_CUENTA_ELIMINADO, HEADER, BODY_CUENTA_AGREGADO,Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_CUENTA_NO_AGREGADO, HEADER, BODY_CUENTA_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }
    }

    private void nuevaCuenta() {
        limpiarCampos();
        txtIdCuenta.setText("");
    }

    private void limpiarCampos() {
        txtIdCuenta.setText("");
        txtBanco.setText("");
        txtNumeroCuenta.setText("");
        cmbTipoCuenta.setValue(null);
    }

    private CuentaDto crearCuentaDto() {
        return new CuentaDto(
                txtIdCuenta.getText(),
                txtBanco.getText(),
                txtNumeroCuenta.getText(),
                cmbTipoCuenta.getValue()
        );
    }

    private boolean datosValidos(CuentaDto cuentaDto) {
        if(cuentaDto.idCuenta().isBlank() ||
                cuentaDto.nombreBanco().isBlank() ||
                cuentaDto.numeroCuenta().isBlank() ||
                cuentaDto.tipoCuenta().isBlank()
        ){
            return false;
        }else{
            return true;
        }
    }

    private void mostrarInformacionUsuario(CuentaDto cuentaSeleccionada) {
        if(cuentaSeleccionada != null){
            txtIdCuenta.setText(cuentaSeleccionada.idCuenta());
            txtBanco.setText(cuentaSeleccionada.nombreBanco());
            txtNumeroCuenta.setText(cuentaSeleccionada.numeroCuenta());
            cmbTipoCuenta.setValue(cuentaSeleccionada.tipoCuenta());
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
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