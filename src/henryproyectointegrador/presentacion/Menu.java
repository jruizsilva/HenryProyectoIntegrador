package henryproyectointegrador.presentacion;

import java.util.Map;
import java.util.Scanner;

import static henryproyectointegrador.utils.ScannerValidator.validOption;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private Map<String, String> opcionesValidas;

    public Menu() {
    }

    public Menu(Map<String, String> opcionesValidas) {
        this.opcionesValidas = opcionesValidas;
    }

    public static Scanner getScanner() {
        return Menu.scanner;
    }

    public static void cerrarScanner() {
        Menu.scanner.close();
    }

    public String mostrarMenu() {
        String opcion;
        boolean inputValido = false;
        do {
            for (String key : this.opcionesValidas.keySet()) {
                System.out.println(this.opcionesValidas.get(key));
            }
            System.out.println("Seleccione una opción: ");
            opcion = Menu.scanner.next();
            if (validOption(opcion, this.opcionesValidas.keySet()
                                                        .toArray(new String[0]))) {
                inputValido = true;
            }

            if (!inputValido)
                System.out.printf("\nOpcion \"%s\" no valida, intente de nuevo.\n", opcion);
        } while (!opcion.equals("5") && !inputValido);
        return opcion;
    }
}
