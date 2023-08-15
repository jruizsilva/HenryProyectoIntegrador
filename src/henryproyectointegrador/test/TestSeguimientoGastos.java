package henryproyectointegrador.test;

import henryproyectointegrador.domain.CategoriaGasto;
import henryproyectointegrador.domain.Gasto;
import henryproyectointegrador.interfaces.ISeguimientoGastos;
import henryproyectointegrador.negocio.SeguimientoGastos;
import henryproyectointegrador.presentacion.Presentacion;

import java.util.Date;
import java.util.List;

public class TestSeguimientoGastos {
    public static void main(String[] args) {
        Presentacion presentacion = Presentacion.getInstance();
        ISeguimientoGastos seguimientoGastos = SeguimientoGastos.getInstancia();

        seguimientoGastos.agregarGasto(1000, CategoriaGasto.COMPRAS, new Date(2021, 10, 10));
        seguimientoGastos.agregarGasto(500, CategoriaGasto.PAGO_SERVICIOS, new Date(2021, 12, 15));
        seguimientoGastos.agregarGasto(800, CategoriaGasto.PAGO_SERVICIOS, new Date(2021, 1, 10));

        List<Gasto> gastos = seguimientoGastos.obtenerGastos();

        presentacion.print(gastos, Gasto.class);
    }
}
