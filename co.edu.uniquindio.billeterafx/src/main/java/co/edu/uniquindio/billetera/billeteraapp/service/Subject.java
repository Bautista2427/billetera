package co.edu.uniquindio.billetera.billeteraapp.service;

public interface Subject {
    void agregarObserver(Observer observer);
    void eliminarObserver(Observer observer);
    void notificarObservers();
}
