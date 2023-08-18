package henryproyectointegrador.dao.dto;

import java.util.Date;

public class ExpenseDto {
    double monto;
    long categoriaId;
    Date fecha;

    public ExpenseDto(double monto, long categoriaId, Date fecha) {
        this.monto = monto;
        this.categoriaId = categoriaId;
        this.fecha = fecha;
    }

    public double getMonto() {
        return this.monto;
    }

    public long getCategoriaId() {
        return this.categoriaId;
    }

    public Date getFecha() {
        return this.fecha;
    }
}
