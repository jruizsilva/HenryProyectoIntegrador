package henryproyectointegrador.presentacion;

import henryproyectointegrador.utils.ScannerValidator;

import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static final ScannerValidator scannerValidator = ScannerValidator.getInstance();
    private Map<String, String> opcionesValidas;

    public Menu() {
    }

    public Menu(Map<String, String> opcionesValidas) {
        this.opcionesValidas = opcionesValidas;
    }

    public String mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        String opcion;
        boolean inputValido = false;
        do {
            for (String key : this.opcionesValidas.keySet()) {
                System.out.println(this.opcionesValidas.get(key));
            }
            System.out.println("Seleccione una opción: ");
            opcion = scanner.next();
            if (Menu.scannerValidator.validOption(opcion, this.opcionesValidas.keySet()
                                                                              .toArray(new String[0]))) {
                inputValido = true;
            }

            if (!inputValido)
                System.out.printf("\nOpcion \"%s\" no valida, intente de nuevo.\n", opcion);
        } while (!opcion.equals("5") && !inputValido);
        scanner.close();
        return opcion;
    }
}
