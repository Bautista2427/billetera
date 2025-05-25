package co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

import co.edu.uniquindio.billetera.billeteraapp.factory.ModelFactory;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.model.builder.UsuarioBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class RegisterViewController {

    @FXML
    private Button btnRegistrar;

    @FXML
    private ImageView imgBilletico;

    @FXML
    private Label lbMensaje;

    @FXML
    private PasswordField pdContrasena;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void OnRegistrar(ActionEvent event) {
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();
        String contrasena = pdContrasena.getText();

        if (cedula.isEmpty() || nombre.isEmpty() || contrasena.isEmpty()) {
            lbMensaje.setText("Cédula, nombre y contraseña son obligatorios.");
            lbMensaje.setStyle("-fx-text-fill: red;");
            return;
        }

        Usuario nuevoUsuario = new UsuarioBuilder()
                .cedula(cedula)
                .nombreCompleto(nombre)
                .correoElectronico(correo)
                .direccion(direccion)
                .numeroTelefono(telefono)
                .contrasena(contrasena)
                .esAdmin(false)
                .build();

        boolean registrado = ModelFactory.getInstancia().getGestorUsuarios().crearUsuario(nuevoUsuario);

        if (registrado) {
            lbMensaje.setText("Usuario registrado exitosamente.");
            lbMensaje.setStyle("-fx-text-fill: #36e43f;");
            limpiarCampos();
        } else {
            lbMensaje.setText("Error: no se pudo registrar el usuario.");
            lbMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    private void limpiarCampos() {
        txtCedula.clear();
        txtNombre.clear();
        txtCorreo.clear();
        txtDireccion.clear();
        txtTelefono.clear();
        pdContrasena.clear();
    }

}