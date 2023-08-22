package henryproyectointegrador.presentacion;

import henryproyectointegrador.dao.dto.CategoryDto;
import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.negocio.ExpenseMonitoring;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static henryproyectointegrador.utils.ScannerInput.*;

public class Presentacion {
    private static final GastoListPrinter gastoListPrinter = GastoListPrinter.getInstance();
    private static final CategoryListPrinter categoryListPrinter = CategoryListPrinter.getInstance();
    private static final ExpenseMonitoring expenseMonitoring = new ExpenseMonitoring();
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
        Menu menuPrincipal = this.crearMenuPrincipal();
        String menuPrincipalOpcionSeleccionada;

        do {
            System.out.println("\n========= Menu principal ===========");
            menuPrincipalOpcionSeleccionada = menuPrincipal.mostrarMenu();
            switch (menuPrincipalOpcionSeleccionada) {
                case "1" -> {
                    Menu menuAgregarGasto = this.crearMenuAgregarGasto();
                    Menu menuCategoria = this.crearMenuCategoria();
                    Menu menuConfirmarGuardar = this.crearMenuConfirmar();
                    String menuAgregarGastoOpcionSeleccionada;
                    double monto = 0;
                    int categoriaGasto = 0;
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
                                String menuGuardarGastoOpcionSeleccionada = menuConfirmarGuardar.mostrarMenu();
                                if (menuGuardarGastoOpcionSeleccionada.equals("1")) {
                                    ExpenseDto expenseDto = new ExpenseDto();
                                    expenseDto.setAmount(monto);
                                    expenseDto.setIdCategory(categoriaGasto);
                                    expenseDto.setDate(fecha);
                                    expenseMonitoring.insert(expenseDto);
                                    System.out.println("Gasto guardado correctamente");
                                }
                            }
                            case "5" -> {
                            }
                        }
                    } while (!menuAgregarGastoOpcionSeleccionada.equals("5"));
                }
                case "2" -> {
                    Menu menuModificarGasto = this.crearMenuModificarGasto();
                    Menu menuCategoria = this.crearMenuCategoria();
                    Menu menuConfirmarActualizacion = this.crearMenuConfirmar();
                    Menu subMenuModificarGasto = this.crearSubMenuModificarGasto();
                    String menuModificarGastoOpcionSeleccionada;
                    do {
                        System.out.println("\n========= Menu - Modificar gasto ===========");
                        menuModificarGastoOpcionSeleccionada = menuModificarGasto.mostrarMenu();
                        switch (menuModificarGastoOpcionSeleccionada) {
                            case "1" -> {
                                List<ExpenseDto> expenses = expenseMonitoring.selectAll();
                                printExpenses(expenses);
                            }
                            case "2" -> {
                                int idExpense = solicitarInt("Ingresa el id del gasto a modificar: ");
                                ExpenseDto expense = expenseMonitoring.selectOne(idExpense);
                                System.out.printf("Gasto a modificar: %s\n", expense);
                                double amount = expense.getAmount();
                                int categoryId = expense.getIdCategory();
                                Date date = expense.getDate();
                                String menuModificarGastoSubMenuOpcionSeleccionada;
                                String menuConfirmarActualizacionOpcionSeleccionada;
                                boolean salirSubmenuModificarGasto = false;
                                do {
                                    menuModificarGastoSubMenuOpcionSeleccionada = subMenuModificarGasto.mostrarMenu();
                                    switch (menuModificarGastoSubMenuOpcionSeleccionada) {
                                        case "1" -> {
                                            System.out.println("\n---------- Actualizar gasto ----------");
                                            amount = solicitarDouble("Ingresa el nuevo monto del gasto: ");
                                            System.out.printf("Monto asignado: %.2f\n", amount);
                                        }
                                        case "2" -> {
                                            System.out.println("\n---------- Actualizar categoria ----------");
                                            String menuCategoriaOpcionSeleccionada = menuCategoria.mostrarMenu();
                                            categoryId = obtenerCategoriaGasto(menuCategoriaOpcionSeleccionada);
                                            System.out.printf("Categoria asignada: %s\n", categoryId);
                                        }
                                        case "3" -> {
                                            System.out.println("\n---------- Actualizar fecha ----------");
                                            date = solicitarFecha("Ingresa la nueva fecha del gasto siguiendo el formato dd/MM/yyyy: ");
                                            System.out.printf("Fecha asignada: %s\n", date);
                                        }
                                        case "4" -> {
                                            System.out.println("\n---------- Guardar cambios  ----------");
                                            System.out.println("Datos del gasto a actualizar");
                                            System.out.println("Monto: " + amount);
                                            System.out.println("Categoria: " + categoryId);
                                            System.out.println("Fecha: " + date);
                                            System.out.println("¿Desea actualizar el gasto? (1. Si / 5. No)");
                                            menuConfirmarActualizacionOpcionSeleccionada = menuConfirmarActualizacion.mostrarMenu();
                                            if (menuConfirmarActualizacionOpcionSeleccionada.equals("1")) {
                                                /*expenseMonitoring.updateExpense();*/
                                                salirSubmenuModificarGasto = true;
                                                System.out.println("Gasto actualizado correctamente");
                                            }
                                        }
                                        case "5" -> salirSubmenuModificarGasto = true;
                                    }
                                } while (!salirSubmenuModificarGasto);
                            }
                            case "5" -> {

                            }
                        }
                    } while (!menuModificarGastoOpcionSeleccionada.equals("5"));
                }

                case "3" -> {
                    Menu menuEliminarGasto = this.crearMenuEliminarGasto();
                    Menu menuConfirmarEliminarGasto = this.crearMenuConfirmar();
                    String menuEliminarGastoOpcionSeleccionada;
                    do {
                        System.out.println("\n========= Menu - Eliminar gasto ===========");
                        menuEliminarGastoOpcionSeleccionada = menuEliminarGasto.mostrarMenu();
                        switch (menuEliminarGastoOpcionSeleccionada) {
                            case "1" -> {
                                List<ExpenseDto> expenses = expenseMonitoring.selectAll();
                                printExpenses(expenses);
                            }
                            case "2" -> {
                                int idGasto = solicitarInt("Ingresa el id del gasto a eliminar: ");
                                ExpenseDto gasto = expenseMonitoring.selectOne(idGasto);
                                System.out.printf("Gasto a eliminar: %s\n", gasto);
                                System.out.println("¿Desea eliminar el gasto? (1. Si / 5. No)");
                                String menuConfirmarEliminarGastoOpcionSeleccionada = menuConfirmarEliminarGasto.mostrarMenu();
                                if (menuConfirmarEliminarGastoOpcionSeleccionada.equals("1")) {
                                    ExpenseDto expenseDto = new ExpenseDto();
                                    expenseDto.setId(idGasto);
                                    expenseMonitoring.delete(expenseDto);
                                    System.out.println("Gasto eliminado correctamente");
                                }
                            }
                            case "5" -> {

                            }
                        }
                    } while (!menuEliminarGastoOpcionSeleccionada.equals("5"));
                }

                case "4" -> {
                    Menu menuMostrarGastos = this.crearMenuMostrarGastos();
                    String menuMostrarGastosOpcionSeleccionada;
                    System.out.println("\n========= Menu - Mostrar gastos ===========");
                    do {
                        menuMostrarGastosOpcionSeleccionada = menuMostrarGastos.mostrarMenu();
                        switch (menuMostrarGastosOpcionSeleccionada) {
                            case "1" -> {
                                List<ExpenseDto> gastos = expenseMonitoring.selectAll();
                                printExpenses(gastos);
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

    private Menu crearMenuPrincipal() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Agregar gasto");
        opcionesValidas.put("2", "2. Modificar gasto");
        opcionesValidas.put("3", "3. Eliminar gasto");
        opcionesValidas.put("4", "4. Mostrar gastos");
        opcionesValidas.put("5", "5. Salir");
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
        String opcion1Descripcion = String.format("1. %s", "test compras");
        String opcion2Descripcion = String.format("2. %s", "to refactor");
        opcionesValidas.put("1", opcion1Descripcion);
        opcionesValidas.put("2", opcion2Descripcion);
        return new Menu(opcionesValidas);
    }

    private Menu crearMenuConfirmar() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Si");
        opcionesValidas.put("5", "5. No");
        return new Menu(opcionesValidas);
    }

    private int obtenerCategoriaGasto(String opcionSeleccionada) {
        return switch (opcionSeleccionada) {
            case "1" -> 1;
            case "2" -> 2;
            default -> 0;
        };
    }

    private Menu crearMenuModificarGasto() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Mostrar gastos");
        opcionesValidas.put("2", "2. Seleccionar id del gasto a modificar");
        opcionesValidas.put("5", "5. Volver al menu principal");
        return new Menu(opcionesValidas);
    }

    private Menu crearSubMenuModificarGasto() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Actualizar monto");
        opcionesValidas.put("2", "2. Actualizar categoria");
        opcionesValidas.put("3", "3. Actualizar fecha");
        opcionesValidas.put("4", "4. Guardar cambios");
        opcionesValidas.put("5", "5. Volver al menu principal");
        return new Menu(opcionesValidas);
    }

    public void printExpenses(List<ExpenseDto> list) {
        gastoListPrinter.print(list);
    }

    private Menu crearMenuEliminarGasto() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Mostrar gastos");
        opcionesValidas.put("2", "2. Ingresar id del gasto a eliminar");
        opcionesValidas.put("5", "5. Volver al menu principal");
        return new Menu(opcionesValidas);
    }

    private Menu crearMenuMostrarGastos() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Mostrar todos los gastos");
        opcionesValidas.put("5", "5. Volver al menu principal");
        return new Menu(opcionesValidas);
    }

    public void printCategories(List<CategoryDto> list) {
        categoryListPrinter.print(list);
    }
}
