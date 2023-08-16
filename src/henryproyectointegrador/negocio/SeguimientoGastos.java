package henryproyectointegrador.negocio;

import henryproyectointegrador.domain.CategoriaGasto;
import henryproyectointegrador.domain.Gasto;
import henryproyectointegrador.interfaces.ISeguimientoGastos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeguimientoGastos implements ISeguimientoGastos {
    private static SeguimientoGastos instancia;
    private final List<Gasto> gastos = new ArrayList<>();

    private SeguimientoGastos() {
    }

    public synchronized static SeguimientoGastos getInstancia() {
        if (SeguimientoGastos.instancia == null) {
            SeguimientoGastos.instancia = new SeguimientoGastos();
        }
        return SeguimientoGastos.instancia;
    }

    @Override
    public void agregarGasto(double monto, CategoriaGasto categoriaGasto, Date fecha) {
        Gasto gasto = new Gasto(monto, categoriaGasto, fecha);
        this.gastos.add(gasto);
    }

    @Override
    public void eliminarGasto(int id_gasto) {
        for (Gasto gasto : this.gastos) {
            if (gasto.getId_gasto() == id_gasto) {
                this.gastos.remove(gasto);
                break;
            }
        }
    }

    @Override
    public void modificarGasto(int id_gasto, double monto, CategoriaGasto categoriaGasto, Date fecha) {
        for (Gasto gasto : this.gastos) {
            if (gasto.getId_gasto() == id_gasto) {
                gasto.setMonto(monto);
                gasto.setCategoriaGasto(categoriaGasto);
                gasto.setFecha(fecha);
                break;
            }
        }
    }

    @Override
    public List<Gasto> obtenerGastos() {
        return this.gastos;
    }

    @Override
    public Gasto obtenerGasto(int id_gasto) {
        for (Gasto gasto : this.gastos) {
            if (gasto.getId_gasto() == id_gasto) {
                return gasto;
            }
        }
        return null;
    }
}
