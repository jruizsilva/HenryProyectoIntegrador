package henryproyectointegrador.presentacion;

import henryproyectointegrador.domain.CategoriaGasto;
import henryproyectointegrador.domain.Gasto;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static henryproyectointegrador.utils.ScannerInput.cerrarScanner;
import static henryproyectointegrador.utils.ScannerInput.solicitarDouble;

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
        Menu menuPrincipal = this.crearMenuPrincipal();
        String menuPrincipalOpcionSeleccionada = menuPrincipal.mostrarMenu();

        switch (menuPrincipalOpcionSeleccionada) {
            case "1": {
                System.out.println("\nOpcion seleccionada \"1\" - Agregar gasto");
                Menu menuAgregarGasto = this.crearMenuAgregarGasto();
                Menu menuCategoria = this.crearMenuCategoria();
                String menuAgregarGastoOpcionSeleccionada = menuAgregarGasto.mostrarMenu();

                switch (menuAgregarGastoOpcionSeleccionada) {
                    case "1":
                        System.out.println("\nOpcion seleccionada \"1\" - Asignar gasto");
                        double monto = solicitarDouble("Ingresa el monto del gasto: ");
                        System.out.println("monto = " + monto);
                }

                break;
            }
            case "5": {
                cerrarScanner();
                break;
            }
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private Menu crearMenuPrincipal() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Agregar gasto");
        opcionesValidas.put("5", "5. Salir");
        return new Menu(opcionesValidas);
    }

    private Menu crearMenuAgregarGasto() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Asignar monto");
        opcionesValidas.put("2", "2. Asignar categoria");
        opcionesValidas.put("3", "3. Asignar fecha");
        opcionesValidas.put("4", "4. Guardar");
        opcionesValidas.put("5", "5. Cancelar");
        return new Menu(opcionesValidas);
    }

    private Menu crearMenuCategoria() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", String.valueOf(CategoriaGasto.COMPRAS));
        opcionesValidas.put("2", String.valueOf(CategoriaGasto.PAGO_SERVICIOS));
        return new Menu(opcionesValidas);
    }
}
