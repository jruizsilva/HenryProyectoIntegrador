package henryproyectointegrador.presentacion;

import henryproyectointegrador.domain.Gasto;

import java.util.List;

public class Presentacion {
    private static final GastoListPrinter gastoListPrinter = GastoListPrinter.getInstance();
    private static final Menu menuPrincipal = new Menu("1", "5");
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
        gastoListPrinter.print(list, clase);
    }

    public void initConsoleMenu() {
        String opcionSeleccionada = menuPrincipal.mostrarMenuOpciones();
        System.out.println("opcionSeleccionada = " + opcionSeleccionada);
    }
}
