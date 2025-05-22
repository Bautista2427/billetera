package co.edu.uniquindio.billetera.billeteraapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BilleteraApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BilleteraApplication.class.getResource("Billetera.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Billetico");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}