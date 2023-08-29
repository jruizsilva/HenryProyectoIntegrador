package henryproyectointegrador.presentacion.menus;

import henryproyectointegrador.negocio.ExpenseMonitoring;

import java.util.Map;
import java.util.TreeMap;

public class MenuFactory {
    private static MenuFactory menuFactory;
    private static ExpenseMonitoring expenseMonitoring;

    private MenuFactory() {
        expenseMonitoring = ExpenseMonitoring.getInstance();
    }

    public static MenuFactory getInstance() {
        if (menuFactory == null) {
            menuFactory = new MenuFactory();
        }
        return menuFactory;
    }

    public Menu createMainMenu() {
        Map<String, String> validOptions = new TreeMap<>();
        validOptions.put("1", "1. Agregar gasto");
        validOptions.put("2", "2. Modificar gasto");
        validOptions.put("3", "3. Eliminar gasto");
        validOptions.put("4", "4. Mostrar gastos");
        validOptions.put("5", "5. Salir");
        return new Menu(validOptions);
    }

    public Menu createAddMenu() {
        Map<String, String> validOptions = new TreeMap<>();
        validOptions.put("1", "1. Asignar monto");
        validOptions.put("2", "2. Asignar categoria");
        validOptions.put("3", "3. Asignar fecha");
        validOptions.put("4", "4. Guardar");
        validOptions.put("5", "5. Volver al menu principal");
        return new Menu(validOptions);
    }

    public Menu createConfirmMenu() {
        Map<String, String> validOptions = new TreeMap<>();
        validOptions.put("1", "1. Si");
        validOptions.put("5", "5. No");
        return new Menu(validOptions);
    }

    public Menu createUpdateMenu() {
        Map<String, String> validOptions = new TreeMap<>();
        validOptions.put("1", "1. Mostrar gastos");
        validOptions.put("2", "2. Seleccionar id del gasto a modificar");
        validOptions.put("5", "5. Volver al menu principal");
        return new Menu(validOptions);
    }

    public Menu createUpdateSubMenu() {
        Map<String, String> validOptions = new TreeMap<>();
        validOptions.put("1", "1. Mostrar gastos");
        validOptions.put("2", "2. Seleccionar id del gasto a modificar");
        validOptions.put("5", "5. Volver al menu principal");
        return new Menu(validOptions);
    }

    public Menu createDeleteMenu() {
        Map<String, String> validOptions = new TreeMap<>();
        validOptions.put("1", "1. Mostrar gastos");
        validOptions.put("2", "2. Ingresar id del gasto a eliminar");
        validOptions.put("5", "5. Volver al menu principal");
        return new Menu(validOptions);
    }

    public Menu createShowDataMenu() {
        Map<String, String> validOptions = new TreeMap<>();
        validOptions.put("1", "1. Mostrar todos los gastos");
        validOptions.put("2", "2. Mostrar gastos por categoria");
        validOptions.put("3", "3. Mostrar gastos por fecha");
        validOptions.put("5", "5. Volver al menu principal");
        return new Menu(validOptions);
    }

    public Menu createDateMenu() {
        Map<String, String> validOptions = new TreeMap<>();
        validOptions.put("1", "1. Mostrar los gastos de hoy");
        validOptions.put("2", "3. Mostrar los gastos de este mes");
        validOptions.put("3", "3. Mostrar los gastos de este año");
        validOptions.put("4", "4. Mostrar los gastos entre dos fechas");
        validOptions.put("5", "5. Volver");
        return new Menu(validOptions);
    }

    public Menu createCategoryMenu() {
        Map<String, String> opcionesValidas = new TreeMap<>();
        Map<Integer, String> categoryList = expenseMonitoring.getCategoryMapList();
        for (Integer key : categoryList.keySet()) {
            String optionDescripcion = String.format("%s. %s", key, categoryList.get(key));
            opcionesValidas.put(String.valueOf(key), optionDescripcion);
        }
        return new Menu(opcionesValidas);
    }
}
