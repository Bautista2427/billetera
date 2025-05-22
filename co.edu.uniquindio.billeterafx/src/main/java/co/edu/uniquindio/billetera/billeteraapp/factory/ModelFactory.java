package co.edu.uniquindio.billetera.billeteraapp.factory;

import co.edu.uniquindio.billetera.billeteraapp.service.IModelFactoryService;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private Usuario usuarioActual;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
}