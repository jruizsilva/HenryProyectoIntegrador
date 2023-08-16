package henryproyectointegrador.test;

import henryproyectointegrador.excepciones.MontoIngresadoInvalidoException;
import henryproyectointegrador.interfaces.ISeguimientoGastos;
import henryproyectointegrador.negocio.SeguimientoGastos;
import henryproyectointegrador.presentacion.Presentacion;

public class TestSeguimientoGastos {
    public static void main(String[] args) {
        Presentacion presentacion = Presentacion.getInstance();
        ISeguimientoGastos seguimientoGastos = SeguimientoGastos.getInstancia();

        /*seguimientoGastos.agregarGasto(1000, CategoriaGasto.COMPRAS, new Date(2021, 10, 10));
        seguimientoGastos.agregarGasto(500, CategoriaGasto.PAGO_SERVICIOS, new Date(2021, 12, 15));
        seguimientoGastos.agregarGasto(800, CategoriaGasto.PAGO_SERVICIOS, new Date(2021, 1, 10));

        List<Gasto> gastos = seguimientoGastos.obtenerGastos();

        presentacion.print(gastos, Gasto.class);*/
        try {
            presentacion.initConsoleMenu();
        } catch (MontoIngresadoInvalidoException e) {
            System.out.println("MontoIngresadoInvalidoException: " + e.getMessage());
        }
    }
}
