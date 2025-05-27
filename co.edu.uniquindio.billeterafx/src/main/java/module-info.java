module co.edu.uniquindio.billetera.billeteraapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens co.edu.uniquindio.billetera.billeteraapp to javafx.fxml;
    exports co.edu.uniquindio.billetera.billeteraapp;

    opens co.edu.uniquindio.billetera.billeteraapp.controller to javafx.fxml;
    exports co.edu.uniquindio.billetera.billeteraapp.controller;

    opens co.edu.uniquindio.billetera.billeteraapp.model;
    exports co.edu.uniquindio.billetera.billeteraapp.model;

    opens co.edu.uniquindio.billetera.billeteraapp.viewcontroller to javafx.fxml;
    exports co.edu.uniquindio.billetera.billeteraapp.viewcontroller;

    exports co.edu.uniquindio.billetera.billeteraapp.factory;
    exports co.edu.uniquindio.billetera.billeteraapp.service;
    exports co.edu.uniquindio.billetera.billeteraapp.mapping.dto;
    exports co.edu.uniquindio.billetera.billeteraapp.model.builder;
}