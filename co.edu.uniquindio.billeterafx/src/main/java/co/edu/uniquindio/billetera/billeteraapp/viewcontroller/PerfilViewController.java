package co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

import co.edu.uniquindio.billetera.billeteraapp.factory.ModelFactory;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PerfilViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private PasswordField pdContrasena;

    @FXML
    private Label lbMensaje;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtCedula;

    @FXML
    private Button btnModificar;

    @FXML
    private TextField txtCorreo;

    private Usuario usuario;

    @FXML
    public void initialize() {
        usuario = ModelFactory.getInstancia().getUsuarioActual();
        if (usuario != null) {
            cargarDatosUsuario();
        }

        btnModificar.setOnAction(this::OnModificar);
    }

    private void cargarDatosUsuario() {
        txtCedula.setText(usuario.getCedula());
        txtCedula.setEditable(false);

        txtNombre.setText(usuario.getNombreCompleto());
        txtCorreo.setText(usuario.getCorreoElectronico());
        txtDireccion.setText(usuario.getDireccion());
        txtNumero.setText(usuario.getNumeroTelefono());
        pdContrasena.setText(usuario.getContrasena());
    }

    @FXML
    private void OnModificar(ActionEvent event) {
        usuario.setNombreCompleto(txtNombre.getText());
        usuario.setCorreoElectronico(txtCorreo.getText());
        usuario.setNumeroTelefono(txtNumero.getText());

        lbMensaje.setText("Usuario actualizado correctamente.");
    }
}
