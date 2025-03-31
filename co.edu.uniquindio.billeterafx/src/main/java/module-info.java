module co.edu.uniquindio.billetera.billeteraapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.billetera.billeteraapp to javafx.fxml;
    exports co.edu.uniquindio.billetera.billeteraapp;
}