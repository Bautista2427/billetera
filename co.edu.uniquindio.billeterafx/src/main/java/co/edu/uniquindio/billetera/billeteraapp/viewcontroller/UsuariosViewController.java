package co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

import co.edu.uniquindio.billetera.billeteraapp.controller.UsuarioController;
import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

import static co.edu.uniquindio.billetera.billeteraapp.utils.Constantes.*;

public class UsuariosViewController {

    UsuarioController usuarioController;
    ObservableList<UsuarioDto> listaUsuarios = FXCollections.observableArrayList();
    UsuarioDto usuarioSeleccionado;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private javafx.scene.control.TableView<UsuarioDto> tableUsuario;

    @FXML
    private TableColumn<UsuarioDto, String> tcCedula;

    @FXML
    private TableColumn<UsuarioDto, String> tcNombre;

    @FXML
    private TableColumn<UsuarioDto, String> tcCorreo;

    @FXML
    private TableColumn<UsuarioDto, String> tcTelefono;

    @FXML
    private TableColumn<UsuarioDto, String> tcDireccion;

    @FXML
    void initialize() {
        usuarioController = new UsuarioController();
        initView();
    }

    @FXML
    void OnNuevo(ActionEvent event) {
        nuevoUsuario();
    }

    @FXML
    void OnAgregar(ActionEvent event) {
        agregarUsuario();
    }

    @FXML
    void OnActualizar(ActionEvent event) {
        actualizarUsuario();
    }

    @FXML
    void OnEliminar(ActionEvent event) {
        eliminarUsuario();
    }

    private void initView() {
        initDataBinding();
        obtenerUsuarios();
        tableUsuario.getItems().clear();
        tableUsuario.setItems(listaUsuarios);
        listenerSelection();
    }

    private void obtenerUsuarios() {
        listaUsuarios.addAll(usuarioController.obtenerUsuarios());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombreCompleto()));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correoElectronico()));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().numeroTelefono()));
        tcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
    }

    private void listenerSelection() {
        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });
    }

    private void agregarUsuario() {
        //1. Captura los datos del formulario
        //2. Armar un Dto con los datos
        UsuarioDto usuarioDto = crearUsuarioDto();
        //3.Validar campos
        if(datosValidos(usuarioDto)){
            //4. Solicitar crear usuario
            if(usuarioController.agregarUsuario(usuarioDto)){
                listaUsuarios.add(usuarioDto);
                limpiarCampos();
                mostrarMensaje(TITULO_USUARIO_AGREGADO, HEADER, BODY_USUARIO_AGREGADO, Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_USUARIO_NO_AGREGADO, HEADER, BODY_USUARIO_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }else{
            //mensaje de notificacion de campos incompletos
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
        }
    }

    private void actualizarUsuario() {
        if (usuarioSeleccionado != null) {
            UsuarioDto usuarioDto = crearUsuarioDto();
            if (usuarioController.actualizarUsuario(usuarioDto)) {
                int index = listaUsuarios.indexOf(usuarioSeleccionado);
                listaUsuarios.set(index, usuarioDto);
                limpiarCampos();
                mostrarMensaje("Usuario actualizado", "", "El usuario se actualizó correctamente", Alert.AlertType.INFORMATION);
            } else {
                mostrarMensaje("Error al actualizar", "", "No se pudo actualizar el usuario", Alert.AlertType.ERROR);
            }
        }
    }

    private void eliminarUsuario() {
        if(usuarioSeleccionado != null){
            if(usuarioController.eliminarUsuario(usuarioSeleccionado.cedula())){
                listaUsuarios.remove(usuarioSeleccionado);
                limpiarCampos();
                mostrarMensaje(TITULO_USUARIO_ELIMINADO, HEADER, BODY_USUARIO_AGREGADO,Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_USUARIO_NO_AGREGADO, HEADER, BODY_USUARIO_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }
    }

    private void nuevoUsuario() {
        limpiarCampos();
        txtNombre.setText("");
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
    }

    private UsuarioDto crearUsuarioDto() {
        return new UsuarioDto(
                txtCedula.getText(),
                "defaultPassword",
                false,
                txtNombre.getText(),
                txtCorreo.getText(),
                txtTelefono.getText(),
                txtDireccion.getText(),
                "0"
        );
    }

    private boolean datosValidos(UsuarioDto usuarioDto) {
        if(usuarioDto.nombreCompleto().isBlank() ||
                usuarioDto.cedula().isBlank() ||
                usuarioDto.correoElectronico().isBlank() ||
                usuarioDto.numeroTelefono().isBlank() ||
                usuarioDto.direccion().isBlank()
        ){
            return false;
        }else{
            return true;
        }
    }

    private void mostrarInformacionUsuario(UsuarioDto usuarioSeleccionado) {
        if(usuarioSeleccionado != null){
            txtNombre.setText(usuarioSeleccionado.nombreCompleto());
            txtCedula.setText(usuarioSeleccionado.cedula());
            txtCorreo.setText(usuarioSeleccionado.correoElectronico());
            txtTelefono.setText(usuarioSeleccionado.numeroTelefono());
            txtDireccion.setText(usuarioSeleccionado.direccion());
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