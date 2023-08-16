package henryproyectointegrador.utils;

import henryproyectointegrador.excepciones.MontoIngresadoInvalidoException;

import java.util.Scanner;

import static henryproyectointegrador.utils.ScannerValidator.isValidDouble;

public class ScannerInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static double solicitarDouble(String textToShow) {
        double entradaDouble = 0;
        boolean isValidInput = false;
        do {
            System.out.println(textToShow);
            String entrada = scanner.next();
            try {
                isValidInput = isValidDouble(entrada);
                entradaDouble = Double.parseDouble(entrada);
            } catch (MontoIngresadoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        } while (!isValidInput);
        return entradaDouble;
    }

    public static String solicitarString() {
        return scanner.next();
    }

    public static void cerrarScanner() {
        ScannerInput.scanner.close();
    }
}
