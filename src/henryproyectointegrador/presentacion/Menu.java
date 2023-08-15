package henryproyectointegrador.presentacion;

import henryproyectointegrador.utils.ScannerValidator;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private static final ScannerValidator scannerValidator = ScannerValidator.getInstance();
    private final String[] opcionesValidas;

    public Menu(String... opcionesValidas) {
        this.opcionesValidas = opcionesValidas;
    }

    public String mostrarMenuOpciones() {
        Scanner scanner = new Scanner(System.in);
        String opcion;
        boolean inputValido = false;
        do {
            System.out.println("1. Agregar gasto");
            System.out.println("5. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = scanner.next();
            if (Menu.scannerValidator.validOption(opcion, this.opcionesValidas))
                inputValido = true;

            if (!inputValido)
                System.out.printf("\nOpcion \"%s\" no valida, intente de nuevo.\n", opcion);
        } while (!opcion.equals("5") && !inputValido);
        scanner.close();
        return opcion;
    }

    public String[] getOpcionesValidas() {
        return this.opcionesValidas;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "opcionesValidas=" + Arrays.toString(opcionesValidas) +
                '}';
    }
}
