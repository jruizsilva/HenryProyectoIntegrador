package henryproyectointegrador.presentacion;

import henryproyectointegrador.domain.CategoriaGasto;
import henryproyectointegrador.domain.Gasto;
import henryproyectointegrador.interfaces.ISeguimientoGastos;
import henryproyectointegrador.negocio.SeguimientoGastos;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static henryproyectointegrador.utils.ScannerInput.*;

public class Presentacion {
    private static final GastoListPrinter gastoListPrinter = GastoListPrinter.getInstance();
    private static final ISeguimientoGastos seguimientoGastos = SeguimientoGastos.getInstancia();
    private static Presentacion instancia;

    private Presentacion() {
    }

    public synchronized static Presentacion getInstance() {
        if (Presentacion.instancia == null) {
            Presentacion.instancia = new Presentacion();
        }
        return Presentacion.instancia;
    }

    public void initConsoleMenu() {
        this.agregarGastosDePrueba();
        Menu menuPrincipal = this.crearMenuPrincipal();
        Menu menuMostrarGastos = this.crearMenuMostrarGastos();
        String menuPrincipalOpcionSeleccionada;

        do {
            System.out.println("\n========= Menu principal ===========");
            menuPrincipalOpcionSeleccionada = menuPrincipal.mostrarMenu();
            switch (menuPrincipalOpcionSeleccionada) {
                case "1" -> {
                    Menu menuAgregarGasto = this.crearMenuAgregarGasto();
                    Menu menuCategoria = this.crearMenuCategoria();
                    Menu menuGuardarGasto = this.crearMenuGuardarGasto();
                    String menuAgregarGastoOpcionSeleccionada;
                    double monto = 0;
                    CategoriaGasto categoriaGasto = null;
                    Date fecha = new Date();
                    do {
                        System.out.println("\n========= Menu - Agregar gasto ===========");
                        menuAgregarGastoOpcionSeleccionada = menuAgregarGasto.mostrarMenu();
                        switch (menuAgregarGastoOpcionSeleccionada) {
                            case "1" -> {
                                System.out.println("\n---------- Asignar gasto ----------");
                                monto = solicitarDouble("Ingresa el monto del gasto: ");
                                System.out.printf("Monto asignado: %.2f\n", monto);
                            }
                            case "2" -> {
                                System.out.println("\n---------- Asignar categoria ----------");
                                String menuCategoriaOpcionSeleccionada = menuCategoria.mostrarMenu();
                                categoriaGasto = obtenerCategoriaGasto(menuCategoriaOpcionSeleccionada);
                                System.out.printf("Categoria asignada: %s\n", categoriaGasto);
                            }
                            case "3" -> {
                                System.out.println("\n---------- Asignar fecha ----------");
                                fecha = solicitarFecha("Ingresa la fecha del gasto siguiendo el formato dd/MM/yyyy: ");
                                System.out.printf("Fecha asignada: %s\n", fecha);
                            }
                            case "4" -> {
                                System.out.println("\n---------- Guardar gasto ----------");
                                System.out.println("Datos del gasto a guardar");
                                System.out.println("Monto: " + monto);
                                System.out.println("Categoria: " + categoriaGasto);
                                System.out.println("Fecha: " + fecha);
                                System.out.println("¿Desea guardar el gasto? (1. Si / 5. No)");
                                String menuGuardarGastoOpcionSeleccionada = menuGuardarGasto.mostrarMenu();
                                if (menuGuardarGastoOpcionSeleccionada.equals("1")) {
                                    seguimientoGastos.agregarGasto(monto, categoriaGasto, fecha);
                                }
                            }
                            case "5" -> {
                            }
                        }
                    } while (!menuAgregarGastoOpcionSeleccionada.equals("5"));
                }
                case "2" -> System.out.println("Modificar gasto");

                case "3" -> System.out.println("Eliminar gasto");

                case "4" -> {
                    System.out.println("\n========= Menu - Mostrar gastos ===========");
                    String menuMostrarGastosOpcionSeleccionada;
                    do {
                        menuMostrarGastosOpcionSeleccionada = menuMostrarGastos.mostrarMenu();
                        switch (menuMostrarGastosOpcionSeleccionada) {
                            case "1" -> {
                                List<Gasto> gastos = seguimientoGastos.obtenerGastos();
                                print(gastos, Gasto.class);
                            }
                            case "5" -> {
                            }
                        }
                    } while (!menuMostrarGastosOpcionSeleccionada.equals("5"));
                }
                case "5" -> cerrarScanner();
            }
        } while (!menuPrincipalOpcionSeleccionada.equals("5"));
    }

    private void agregarGastosDePrueba() {
        seguimientoGastos.agregarGasto(100, CategoriaGasto.COMPRAS, new Date());
        seguimientoGastos.agregarGasto(200, CategoriaGasto.PAGO_SERVICIOS, new Date());
        seguimientoGastos.agregarGasto(300, CategoriaGasto.COMPRAS, new Date());
        seguimientoGastos.agregarGasto(400, CategoriaGasto.PAGO_SERVICIOS, new Date());
        seguimientoGastos.agregarGasto(500, CategoriaGasto.COMPRAS, new Date());
        seguimientoGastos.agregarGasto(600, CategoriaGasto.PAGO_SERVICIOS, new Date());
        seguimientoGastos.agregarGasto(700, CategoriaGasto.COMPRAS, new Date());
        seguimientoGastos.agregarGasto(800, CategoriaGasto.PAGO_SERVICIOS, new Date());
        seguimientoGastos.agregarGasto(900, CategoriaGasto.COMPRAS, new Date());
    }

    private Menu crearMenuPrincipal() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Agregar gasto");
        opcionesValidas.put("2", "2. Modificar gasto");
        opcionesValidas.put("3", "3. Eliminar gasto");
        opcionesValidas.put("4", "4. Mostrar gastos");
        opcionesValidas.put("5", "5. Salir");
        return new Menu(opcionesValidas);
    }

    private Menu crearMenuMostrarGastos() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Mostrar gastos");
        opcionesValidas.put("5", "5. Volver al menu principal");
        return new Menu(opcionesValidas);
    }

    private Menu crearMenuAgregarGasto() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Asignar monto");
        opcionesValidas.put("2", "2. Asignar categoria");
        opcionesValidas.put("3", "3. Asignar fecha");
        opcionesValidas.put("4", "4. Guardar");
        opcionesValidas.put("5", "5. Volver al menu principal");
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

    private Menu crearMenuGuardarGasto() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Si");
        opcionesValidas.put("5", "5. No");
        return new Menu(opcionesValidas);
    }

    private CategoriaGasto obtenerCategoriaGasto(String opcionSeleccionada) {
        return switch (opcionSeleccionada) {
            case "1" -> CategoriaGasto.COMPRAS;
            case "2" -> CategoriaGasto.PAGO_SERVICIOS;
            default -> null;
        };
    }

    private void print(List<? extends Gasto> list, Class clase) {
        gastoListPrinter.print(list, clase);
    }
}
