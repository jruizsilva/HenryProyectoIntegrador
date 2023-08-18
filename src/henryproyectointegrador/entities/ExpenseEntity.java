package henryproyectointegrador.entities;

import henryproyectointegrador.dao.dto.ExpenseDto;

import java.util.Date;

public class ExpenseEntity extends ExpenseDto {
    private final int id_gasto;
    private double monto;
    private long categoria_id;
    private Date fecha;

    public ExpenseEntity(int id_gasto, double monto, long categoria_id, Date fecha) {
        super(monto, categoria_id, fecha);
        this.id_gasto = id_gasto;
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

    @Override
    public long getCategoria_id() {
        return this.categoria_id;
    }

    public void setCategoria_id(long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ExpenseEntity{" +
                "id_gasto=" + id_gasto +
                ", monto=" + monto +
                ", categoriaId=" + categoria_id +
                ", fecha=" + fecha +
                "} " + super.toString();
    }
}
