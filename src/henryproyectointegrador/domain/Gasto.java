package henryproyectointegrador.domain;

import java.util.Date;

public class Gasto {
    private static int instanciasCreadas = 0;
    private final int id_gasto;
    private double monto;
    private CategoriaGasto categoriaGasto;
    private Date fecha;

    public Gasto(double monto, CategoriaGasto categoriaGasto, Date fecha) {
        this();
        this.monto = monto;
        this.categoriaGasto = categoriaGasto;
        this.fecha = fecha;
    }

    private Gasto() {
        this.id_gasto = ++instanciasCreadas;
    }

    public int getId_gasto() {
        return this.id_gasto;
    }

    public double getMonto() {
        return this.monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public CategoriaGasto getCategoriaGasto() {
        return this.categoriaGasto;
    }

    public void setCategoriaGasto(CategoriaGasto categoriaGasto) {
        this.categoriaGasto = categoriaGasto;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Gasto{" +
                "id_gasto=" + id_gasto +
                ", monto=" + monto +
                ", categoriaGasto=" + categoriaGasto +
                '}';
    }
}
