package henryproyectointegrador.presentacion;

import henryproyectointegrador.domain.Gasto;
import henryproyectointegrador.utils.ScannerValidator;

import java.util.List;
import java.util.Scanner;

public class Presentacion {
    private static final GastoListPrinter gastoListPrinter = GastoListPrinter.getInstance();
    private static final ScannerValidator scannerValidator = ScannerValidator.getInstance();
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
        this.mostrarMenuOpciones();
    }

    public void mostrarMenuOpciones() {
        Scanner scanner = new Scanner(System.in);
        List<String> opcionesValidas = List.of("1", "5");
        String opcion;
        boolean inputValido = false;
        do {
            System.out.println("1. Agregar gasto");
            System.out.println("5. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = scanner.next();
            if (scannerValidator.validOption(opcion, opcionesValidas))
                inputValido = true;

            if (!inputValido)
                System.out.printf("\nOpcion \"%s\" no valida, intente de nuevo.\n", opcion);
        } while (!opcion.equals("5") && !inputValido);
        scanner.close();
    }
}
