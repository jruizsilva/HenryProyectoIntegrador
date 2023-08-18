package henryproyectointegrador.interfaces;

import henryproyectointegrador.entities.CategoriaGasto;
import henryproyectointegrador.entities.ExpenseEntity;

import java.util.Date;
import java.util.List;

public interface ISeguimientoGastos {
    void agregarGasto(double monto, CategoriaGasto categoriaGasto, Date fecha);

    void eliminarGasto(int id_gasto);

    void modificarGasto(int id_gasto, double monto, CategoriaGasto categoriaGasto, Date fecha);

    List<ExpenseEntity> obtenerGastos();

    ExpenseEntity obtenerGasto(int id_gasto);
}
