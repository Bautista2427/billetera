package co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

import co.edu.uniquindio.billetera.billeteraapp.factory.ModelFactory;
import co.edu.uniquindio.billetera.billeteraapp.model.GestorUsuarios;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.utils.DataUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginViewController {

    private static final Logger logger = Logger.getLogger(LoginViewController.class.getName());

    @FXML
    private Button btnIngresar;

    @FXML
    private ImageView imgBilletico;

    @FXML
    private Label lbMensaje;

    @FXML
    private PasswordField pdContrasena;

    @FXML
    private TextField txtCedula;

    @FXML
    void OnIngresar(ActionEvent event) {
        String cedula = txtCedula.getText();
        String contrasena = pdContrasena.getText();

        Usuario usuario = null;

        GestorUsuarios gestorUsuarios = ModelFactory.getInstancia().getGestorUsuarios();
        usuario = gestorUsuarios.obtenerUsuarioPorCredenciales(cedula, contrasena);

        if (usuario == null) {
            usuario = DataUtil.validarCredenciales(cedula, contrasena);
        }

        if (usuario != null) {
            ModelFactory.getInstancia().setUsuarioActual(usuario);

            String rutaFXML = usuario.isEsAdmin()
                    ? "/co/edu/uniquindio/billetera/billeteraapp/Admin.fxml"
                    : "/co/edu/uniquindio/billetera/billeteraapp/User.fxml";

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
                Parent root = loader.load();

                Stage stage = (Stage) btnIngresar.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error al cargar la vista", e);
                lbMensaje.setText("Error al cargar la vista.");
            }
        } else {
            lbMensaje.setText("Credenciales inválidas");
        }
    }
}