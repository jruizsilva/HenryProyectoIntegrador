package henryproyectointegrador.presentacion;

import henryproyectointegrador.domain.CategoriaGasto;
import henryproyectointegrador.domain.Gasto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static henryproyectointegrador.utils.ScannerInput.*;

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
        String menuPrincipalOpcionSeleccionada;

        do {
            System.out.println("\n========= Menu principal ===========");
            menuPrincipalOpcionSeleccionada = menuPrincipal.mostrarMenu();
            switch (menuPrincipalOpcionSeleccionada) {
                case "1": {
                    Menu menuAgregarGasto = this.crearMenuAgregarGasto();
                    Menu menuCategoria = this.crearMenuCategoria();
                    String menuAgregarGastoOpcionSeleccionada;
                    double monto;
                    CategoriaGasto categoriaGasto;
                    Date fecha;
                    do {
                        System.out.println("\n========= Menu - Agregar gasto ===========");
                        menuAgregarGastoOpcionSeleccionada = menuAgregarGasto.mostrarMenu();
                        switch (menuAgregarGastoOpcionSeleccionada) {
                            case "1":
                                System.out.println("\n---------- Asignar gasto ----------");
                                monto = solicitarDouble("Ingresa el monto del gasto: ");
                                System.out.printf("Monto asignado: %.2f\n", monto);
                                break;
                            case "2":
                                System.out.println("\n---------- Asignar categoria ----------");
                                String menuCategoriaOpcionSeleccionada = menuCategoria.mostrarMenu();
                                System.out.println("menuCategoriaOpcionSeleccionada = " + menuCategoriaOpcionSeleccionada);
                                break;
                            case "3":
                                System.out.println("\n---------- Asignar fecha ----------");
                                fecha = solicitarFecha("Ingresa la fecha del gasto siguiendo el formato dd/MM/yyyy: ");
                                System.out.printf("Fecha asignada: %s\n", fecha);
                                break;
                            case "4":
                                System.out.println("\n---------- Guardar gasto ----------");
                                break;
                            case "5":
                                break;
                        }
                    } while (!menuAgregarGastoOpcionSeleccionada.equals("5"));

                    break;
                }
                case "5": {
                    cerrarScanner();
                    break;
                }
            }
        } while (!menuPrincipalOpcionSeleccionada.equals("5"));
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
        String opcion1Descripcion = String.format("1. %s", CategoriaGasto.COMPRAS);
        String opcion2Descripcion = String.format("2. %s", CategoriaGasto.PAGO_SERVICIOS);
        opcionesValidas.put("1", opcion1Descripcion);
        opcionesValidas.put("2", opcion2Descripcion);
        return new Menu(opcionesValidas);
    }
}
