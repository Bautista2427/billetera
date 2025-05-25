package co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TransaccionesViewController {

    @FXML
    private TextField txtMonto;

    @FXML
    private Label lbFecha;

    @FXML
    private Label lbTipo;

    @FXML
    private Label lbID;

    @FXML
    private Button btnRealizarTransaccion;

    @FXML
    private ComboBox<?> cbCuentaDestino;

    @FXML
    private ComboBox<?> cbCuentaOrigen;

    @FXML
    private TextArea txtMensaje;

    /**@FXML
    void OnRealizarTransaccion(ActionEvent event) {

    }**/

}
