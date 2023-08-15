package henryproyectointegrador.presentacion;

import henryproyectointegrador.domain.Gasto;

import java.util.List;

public class Presentacion {
    private static final ListPrinter listPrinter = new ListPrinter();
    private static Presentacion instancia;

    private Presentacion() {
    }

    public synchronized static Presentacion getInstance() {
        if (Presentacion.instancia == null) {
            Presentacion.instancia = new Presentacion();
        }
        return Presentacion.instancia;
    }

    public void print(List<? extends Gasto> list, Class clase) {
        listPrinter.print(list, clase);
    }
}
