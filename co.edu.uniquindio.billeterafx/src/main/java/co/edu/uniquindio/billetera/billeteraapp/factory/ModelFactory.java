package co.edu.uniquindio.billetera.billeteraapp.factory;

import co.edu.uniquindio.billetera.billeteraapp.mapping.dto.UsuarioDto;
import co.edu.uniquindio.billetera.billeteraapp.mapping.mappers.PrestamoMappingImpl;
import co.edu.uniquindio.billetera.billeteraapp.model.PrestamoObjeto;
import co.edu.uniquindio.billetera.billeteraapp.service.IModelFactoryService;
import co.edu.uniquindio.billetera.billeteraapp.model.Usuario;
import co.edu.uniquindio.billetera.billeteraapp.service.IPrestamoMapping;
import co.edu.uniquindio.billetera.billeteraapp.utils.DataUtil;

import java.util.List;

public class ModelFactory implements IModelFactoryService {
    private static ModelFactory modelFactory;
    private IPrestamoMapping mapper;
    private PrestamoObjeto prestamoObjeto;
    private Usuario usuarioActual;

    public static ModelFactory getInstancia() {
        if(modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory(){
        mapper = new PrestamoMappingImpl();
        prestamoObjeto = DataUtil.inicializarDatos();
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return mapper.getUsuariosDto(prestamoObjeto.getListaUsuarios());
    }
}