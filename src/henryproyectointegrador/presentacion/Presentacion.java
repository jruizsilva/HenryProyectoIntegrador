package henryproyectointegrador.presentacion;

import henryproyectointegrador.domain.Gasto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Presentacion {
    private static final GastoListPrinter gastoListPrinter = GastoListPrinter.getInstance();
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
        Map<String, String> menuPrincipalOpcionesValidas = new TreeMap<>();
        menuPrincipalOpcionesValidas.put("1", "1. Agregar gasto");
        menuPrincipalOpcionesValidas.put("5", "5. Salir");
        Menu menuPrincipal = new Menu(menuPrincipalOpcionesValidas);

        String opcionSeleccionada = menuPrincipal.mostrarMenuOpciones();
        switch (opcionSeleccionada) {
            case "1" -> System.out.println("Opcion seleccionada \"1\" \nCreando gasto...");
            case "5" -> System.out.println("Opcion seleccionada \"5\"\nSaliendo...");
            default -> System.out.println("Opcion no valida");
        }
    }
}
