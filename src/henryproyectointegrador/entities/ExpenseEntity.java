package henryproyectointegrador.entities;

import henryproyectointegrador.dao.dto.ExpenseDto;

import java.util.Date;

public class ExpenseEntity extends ExpenseDto {
    private final int id_gasto;
    private double monto;
    private long categoriaId;
    private Date fecha;

    public ExpenseEntity(int id_gasto, double monto, long categoriaId, Date fecha) {
        super(monto, categoriaId, fecha);
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
    public long getCategoriaId() {
        return this.categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
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
                ", categoriaId=" + categoriaId +
                ", fecha=" + fecha +
                "} " + super.toString();
    }
}
