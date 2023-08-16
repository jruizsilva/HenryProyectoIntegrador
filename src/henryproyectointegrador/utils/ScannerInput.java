package henryproyectointegrador.utils;

import henryproyectointegrador.excepciones.FechaIngresadaInvalidaException;
import henryproyectointegrador.excepciones.MontoIngresadoInvalidoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static henryproyectointegrador.utils.ScannerValidator.isValidDate;
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

    public static Date solicitarFecha(String textToShow) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date entradaDate = null;
        boolean isValidInput = false;
        do {
            System.out.println(textToShow);
            String entrada = scanner.next();
            try {
                isValidInput = isValidDate(entrada, format);
                entradaDate = format.parse(entrada);
            } catch (FechaIngresadaInvalidaException | ParseException e) {
                System.out.println(e.getMessage());
            }
        } while (!isValidInput);

        return entradaDate;
    }

    public static String solicitarString() {
        return scanner.next();
    }

    public static void cerrarScanner() {
        ScannerInput.scanner.close();
    }
}
