package henryproyectointegrador.interfaces;

import henryproyectointegrador.domain.CategoriaGasto;
import henryproyectointegrador.domain.Gasto;

import java.util.Date;
import java.util.List;

public interface ISeguimientoGastos {
    void agregarGasto(double monto, CategoriaGasto categoriaGasto, Date fecha);

    void eliminarGasto(int id_gasto);

    void modificarGasto(int id_gasto, double monto, CategoriaGasto categoriaGasto, Date fecha);

    List<Gasto> obtenerGastos();
}
