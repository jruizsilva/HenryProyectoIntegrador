package henryproyectointegrador.presentacion;

import henryproyectointegrador.dao.dto.ExpenseDto;
import henryproyectointegrador.negocio.ExpenseMonitoring;
import henryproyectointegrador.presentacion.menus.Menu;
import henryproyectointegrador.presentacion.menus.MenuFactory;

import java.time.LocalDate;
import java.util.List;

import static henryproyectointegrador.utils.ScannerInput.*;

public class Presentacion {
    private static Presentacion presentacion;
    private static ExpenseMonitoring expenseMonitoring;
    private static Printer printer;
    private static MenuFactory menuFactory;
    private static Menu mainMenu;
    private static Menu addMenu;
    private static Menu confirmMenu;
    private static Menu updateMenu;
    private static Menu updateSubMenu;
    private static Menu deleteMenu;
    private static Menu getMenu;
    private static Menu categoryMenu;

    private Presentacion() {
        printer = Printer.getInstance();
        menuFactory = MenuFactory.getInstance();
        expenseMonitoring = ExpenseMonitoring.getInstance();
        initMenus();
    }

    private static void initMenus() {
        mainMenu = menuFactory.createMainMenu();
        addMenu = menuFactory.createAddMenu();
        confirmMenu = menuFactory.createConfirmMenu();
        updateMenu = menuFactory.createUpdateMenu();
        updateSubMenu = menuFactory.createUpdateSubMenu();
        deleteMenu = menuFactory.createDeleteMenu();
        getMenu = menuFactory.createGetMenu();
        categoryMenu = menuFactory.createCategoryMenu();
    }

    public synchronized static Presentacion getInstance() {
        if (Presentacion.presentacion == null) {
            Presentacion.presentacion = new Presentacion();
        }
        return Presentacion.presentacion;
    }

    public void initConsoleMenu() {
        String mainMenuOptSel;

        do {
            System.out.println("\n========= Menu principal ===========");
            mainMenuOptSel = mainMenu.initMenu()
                                     .requestInput();
            switch (mainMenuOptSel) {
                case "1" -> {
                    String addMenuOptSel;
                    Double amount = null;
                    Integer categoryId = null;
                    LocalDate date = LocalDate.now();
                    ExpenseDto expenseDto = new ExpenseDto();
                    expenseDto.setDate(date);
                    do {
                        System.out.println("\n========= Menu - Agregar gasto ===========");
                        addMenuOptSel = addMenu.initMenu()
                                               .requestInput();
                        switch (addMenuOptSel) {
                            case "1" -> {
                                System.out.println("\n---------- Asignar gasto ----------");
                                amount = solicitarDouble("Ingresa el monto del gasto: ");
                                expenseDto.setAmount(amount);
                                System.out.printf("Monto asignado: %.2f\n", amount);
                                printer.print(expenseDto, false);
                            }
                            case "2" -> {
                                System.out.println("\n---------- Asignar categoria ----------");
                                categoryId = Integer.parseInt(categoryMenu.initMenu()
                                                                          .requestInput());
                                expenseDto.setIdCategory(categoryId);
                                System.out.printf("Categoria asignada: %s\n", expenseMonitoring.getCategoryMapList()
                                                                                               .get(categoryId));
                                printer.print(expenseDto, false);
                            }
                            case "3" -> {
                                System.out.println("\n---------- Asignar fecha ----------");
                                date = requestLocalDate("Ingresa la fecha del gasto siguiendo el formato yyyy-MM-dd: ");
                                expenseDto.setDate(date);
                                System.out.printf("Fecha asignada: %s\n", date);
                                printer.print(expenseDto, false);
                            }
                            case "4" -> {
                                if (amount == null) {
                                    System.out.print("field amount is required");
                                    break;
                                }
                                if (categoryId == null) {
                                    System.out.print("field category is required");
                                    break;
                                }
                                if (amount <= 0) {
                                    System.out.printf("amount \"%s\" is not valid", amount);
                                    break;
                                }

                                System.out.println("\n---------- Guardar gasto ----------");
                                printer.print(expenseDto, false);
                                System.out.println("¿Desea guardar el gasto? (1. Si / 5. No)");
                                String confirmMenuOptSel = confirmMenu.initMenu()
                                                                      .requestInput();
                                if (confirmMenuOptSel.equals("1")) {
                                    int rows = expenseMonitoring.insert(expenseDto);
                                    if (rows > 0) {
                                        System.out.println("Gasto guardado correctamente");
                                    }
                                }
                            }
                            case "5" -> {
                            }
                        }
                    } while (!addMenuOptSel.equals("5"));
                }
                case "2" -> {
                    String updateMenuOptSel;
                    do {
                        System.out.println("\n========= Menu - Modificar gasto ===========");
                        updateMenuOptSel = updateMenu.initMenu()
                                                     .requestInput();
                        switch (updateMenuOptSel) {
                            case "1" -> {
                                List<ExpenseDto> expenses = expenseMonitoring.selectAll();
                                printer.printExpenses(expenses, true);
                            }
                            case "2" -> {
                                int idExpense = solicitarInt("Ingresa el id del gasto a modificar: ");
                                ExpenseDto expense = expenseMonitoring.selectOne(idExpense);
                                System.out.println("Gasto a modificar");
                                printer.print(expense, true);
                                ExpenseDto expenseDto = new ExpenseDto();
                                expenseDto.setId(expense.getId());
                                expenseDto.setAmount(expense.getAmount());
                                expenseDto.setIdCategory(expense.getIdCategory());
                                expenseDto.setDate(expense.getDate());
                                String updateSubMenuOptSel;
                                String confirmMenuOptSel;
                                boolean salirSubmenuModificarGasto = false;
                                do {
                                    updateSubMenuOptSel = updateSubMenu.initMenu()
                                                                       .requestInput();
                                    switch (updateSubMenuOptSel) {
                                        case "1" -> {
                                            System.out.println("\n---------- Actualizar gasto ----------");
                                            Double amount = solicitarDouble("Ingresa el nuevo monto del gasto: ");
                                            expenseDto.setAmount(amount);
                                            System.out.printf("Monto asignado: %.2f\n", amount);
                                            printer.print(expenseDto, true);
                                        }
                                        case "2" -> {
                                            System.out.println("\n---------- Actualizar categoria ----------");
                                            String menuCategoriaOpcionSeleccionada = categoryMenu.initMenu()
                                                                                                 .requestInput();
                                            Integer categoryId = Integer.parseInt(menuCategoriaOpcionSeleccionada);
                                            expenseDto.setIdCategory(categoryId);
                                            System.out.printf("Categoria asignada: %s\n", expenseMonitoring.getCategoryMapList()
                                                                                                           .get(categoryId));
                                            printer.print(expenseDto, true);
                                        }
                                        case "3" -> {
                                            System.out.println("\n---------- Actualizar fecha ----------");
                                            LocalDate date = requestLocalDate("Ingresa la nueva fecha del gasto siguiendo el formato dd/MM/yyyy: ");
                                            expenseDto.setDate(date);
                                            System.out.printf("Fecha asignada: %s\n", date);
                                            printer.print(expenseDto, true);
                                        }
                                        case "4" -> {
                                            if (expenseDto.getAmount() == null) {
                                                System.out.println("field amount is required");
                                                break;
                                            }
                                            if (expenseDto.getIdCategory() == null) {
                                                System.out.println("field category is required");
                                                break;
                                            }
                                            if (expenseDto.getAmount() <= 0) {
                                                System.out.printf("amount \"%s\" is not valid\n", expenseDto.getAmount());
                                                break;
                                            }
                                            System.out.println("\n---------- Guardar cambios  ----------");
                                            System.out.println("Datos del gasto a actualizar");
                                            printer.print(expenseDto, true);
                                            System.out.println("¿Desea actualizar el gasto? (1. Si / 5. No)");
                                            confirmMenuOptSel = confirmMenu.initMenu()
                                                                           .requestInput();
                                            if (confirmMenuOptSel.equals("1")) {
                                                int rows = expenseMonitoring.update(expenseDto);
                                                if (rows > 0) {
                                                    System.out.println("Gasto actualizado correctamente");
                                                    salirSubmenuModificarGasto = true;
                                                }
                                            }
                                        }
                                        case "5" -> salirSubmenuModificarGasto = true;
                                    }
                                } while (!salirSubmenuModificarGasto);
                            }
                            case "5" -> {

                            }
                        }
                    } while (!updateMenuOptSel.equals("5"));
                }

                case "3" -> {
                    String deleteMenuOptSel;
                    do {
                        System.out.println("\n========= Menu - Eliminar gasto ===========");
                        deleteMenuOptSel = deleteMenu.initMenu()
                                                     .requestInput();
                        switch (deleteMenuOptSel) {
                            case "1" -> {
                                List<ExpenseDto> expenses = expenseMonitoring.selectAll();
                                printer.printExpenses(expenses, true);
                            }
                            case "2" -> {
                                int idExpense = solicitarInt("Ingresa el id del gasto a eliminar: ");
                                ExpenseDto expense = expenseMonitoring.selectOne(idExpense);
                                printer.print(expense, true);
                                System.out.println("¿Desea eliminar el gasto? (1. Si / 5. No)");
                                String menuConfirmarEliminarGastoOpcionSeleccionada = confirmMenu.initMenu()
                                                                                                 .requestInput();
                                if (menuConfirmarEliminarGastoOpcionSeleccionada.equals("1")) {
                                    ExpenseDto expenseDto = new ExpenseDto();
                                    expenseDto.setId(idExpense);
                                    int rows = expenseMonitoring.delete(expenseDto);
                                    if (rows > 0) {
                                        System.out.println("Gasto eliminado correctamente");
                                    }
                                }
                            }
                            case "5" -> {

                            }
                        }
                    } while (!deleteMenuOptSel.equals("5"));
                }

                case "4" -> {
                    String getMenuOptSel;
                    System.out.println("\n========= Menu - Mostrar gastos ===========");
                    do {
                        getMenuOptSel = getMenu.initMenu()
                                               .requestInput();
                        switch (getMenuOptSel) {
                            case "1" -> {
                                List<ExpenseDto> gastos = expenseMonitoring.selectAll();
                                printer.printExpenses(gastos, true);
                            }
                            case "5" -> {
                            }
                        }
                    } while (!getMenuOptSel.equals("5"));
                }
                case "5" -> cerrarScanner();
            }
        } while (!mainMenuOptSel.equals("5"));
    }
}
