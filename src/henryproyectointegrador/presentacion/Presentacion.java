package henryproyectointegrador.presentacion;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.negocio.ExpenseMonitoring;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static henryproyectointegrador.utils.ScannerInput.*;

public class Presentacion {
    private static final Printer printer = Printer.getInstance();
    private static final ExpenseMonitoring expenseMonitoring = ExpenseMonitoring.getInstance();
    private static Presentacion presentacion;

    private Presentacion() {
    }

    public synchronized static Presentacion getInstance() {
        if (Presentacion.presentacion == null) {
            Presentacion.presentacion = new Presentacion();
        }
        return Presentacion.presentacion;
    }

    public void initConsoleMenu() {
        Menu menuPrincipal = this.crearMenuPrincipal();
        String menuPrincipalOpcionSeleccionada;

        do {
            System.out.println("\n========= Menu principal ===========");
            menuPrincipalOpcionSeleccionada = menuPrincipal.mostrarMenu();
            switch (menuPrincipalOpcionSeleccionada) {
                case "1" -> {
                    Menu menuAgregarGasto = crearMenuAgregarGasto();
                    Menu menuCategoria = crearMenuCategoria();
                    Menu menuConfirmarGuardar = crearMenuConfirmar();
                    String menuAgregarGastoOpcionSeleccionada;
                    Double amount = null;
                    Integer categoryId = null;
                    LocalDate date = LocalDate.now();
                    System.out.println(date);
                    ExpenseDto expenseDto = new ExpenseDto();
                    expenseDto.setDate(date);
                    do {
                        System.out.println("\n========= Menu - Agregar gasto ===========");
                        menuAgregarGastoOpcionSeleccionada = menuAgregarGasto.mostrarMenu();
                        switch (menuAgregarGastoOpcionSeleccionada) {
                            case "1" -> {
                                System.out.println("\n---------- Asignar gasto ----------");
                                amount = solicitarDouble("Ingresa el monto del gasto: ");
                                expenseDto.setAmount(amount);
                                System.out.printf("Monto asignado: %.2f\n", amount);
                                printer.print(expenseDto, false);
                            }
                            case "2" -> {
                                System.out.println("\n---------- Asignar categoria ----------");
                                categoryId = Integer.parseInt(menuCategoria.mostrarMenu());
                                expenseDto.setIdCategory(categoryId);
                                System.out.printf("Categoria asignada: %s\n", expenseMonitoring.getCategoryMapList()
                                                                                               .get(categoryId));
                                printer.print(expenseDto, false);
                            }
                            case "3" -> {
                                System.out.println("\n---------- Asignar fecha ----------");
                                date = requestLocalDate("Ingresa la fecha del gasto siguiendo el formato yyyy-MM-dd: ");
                                System.out.println(date);
                                expenseDto.setDate(date);
                                System.out.printf("Fecha asignada: %s\n", date);
                                printer.print(expenseDto, false);
                            }
                            case "4" -> {
                                if (amount == null) {
                                    System.out.println("field amount is required");
                                    break;
                                }
                                if (categoryId == null) {
                                    System.out.println("field category is required");
                                    break;
                                }
                                if (amount <= 0) {
                                    System.out.printf("amount \"%s\" is not valid", amount);
                                    break;
                                }

                                System.out.println("\n---------- Guardar gasto ----------");
                                printer.print(expenseDto, false);
                                System.out.println("¿Desea guardar el gasto? (1. Si / 5. No)");
                                String menuGuardarGastoOpcionSeleccionada = menuConfirmarGuardar.mostrarMenu();
                                if (menuGuardarGastoOpcionSeleccionada.equals("1")) {
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
                    Menu menuModificarGasto = crearMenuModificarGasto();
                    Menu menuCategoria = crearMenuCategoria();
                    Menu menuConfirmarActualizacion = crearMenuConfirmar();
                    Menu subMenuModificarGasto = crearSubMenuModificarGasto();
                    String menuModificarGastoOpcionSeleccionada;
                    do {
                        System.out.println("\n========= Menu - Modificar gasto ===========");
                        menuModificarGastoOpcionSeleccionada = menuModificarGasto.mostrarMenu();
                        switch (menuModificarGastoOpcionSeleccionada) {
                            case "1" -> {
                                List<ExpenseDto> expenses = expenseMonitoring.selectAll();
                                printer.printExpenses(expenses, true);
                            }
                            case "2" -> {
                                int idExpense = solicitarInt("Ingresa el id del gasto a modificar: ");
                                ExpenseDto expense = expenseMonitoring.selectOne(idExpense);
                                System.out.printf("Gasto a modificar: %s\n", expense);
                                double amount = expense.getAmount();
                                int categoryId = expense.getIdCategory();
                                LocalDate date = expense.getDate();
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
                                            categoryId = Integer.parseInt(menuCategoriaOpcionSeleccionada);
                                            System.out.printf("Categoria asignada: %s\n", categoryId);
                                        }
                                        case "3" -> {
                                            System.out.println("\n---------- Actualizar fecha ----------");
                                            date = requestLocalDate("Ingresa la nueva fecha del gasto siguiendo el formato dd/MM/yyyy: ");
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
                    Menu menuEliminarGasto = crearMenuEliminarGasto();
                    Menu menuConfirmarEliminarGasto = crearMenuConfirmar();
                    String menuEliminarGastoOpcionSeleccionada;
                    do {
                        System.out.println("\n========= Menu - Eliminar gasto ===========");
                        menuEliminarGastoOpcionSeleccionada = menuEliminarGasto.mostrarMenu();
                        switch (menuEliminarGastoOpcionSeleccionada) {
                            case "1" -> {
                                List<ExpenseDto> expenses = expenseMonitoring.selectAll();
                                printer.printExpenses(expenses, true);
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
                                printer.printExpenses(gastos, true);
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
        Map<Integer, String> categoryList = expenseMonitoring.getCategoryMapList();
        for (Integer key : categoryList.keySet()) {
            String optionDescripcion = String.format("%s. %s", key, categoryList.get(key));
            opcionesValidas.put(String.valueOf(key), optionDescripcion);
        }
        return new Menu(opcionesValidas);
    }

    private Menu crearMenuConfirmar() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        opcionesValidas.put("1", "1. Si");
        opcionesValidas.put("5", "5. No");
        return new Menu(opcionesValidas);
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
}
