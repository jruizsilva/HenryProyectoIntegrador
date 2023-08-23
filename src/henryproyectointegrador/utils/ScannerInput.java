package henryproyectointegrador.utils;

import henryproyectointegrador.excepciones.FechaIngresadaInvalidaException;
import henryproyectointegrador.excepciones.IdGastoIngresadoInvalidoException;
import henryproyectointegrador.excepciones.MontoIngresadoInvalidoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import static henryproyectointegrador.utils.ScannerValidator.*;

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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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

    public static LocalDate requestLocalDate(String textToShow) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = null;
        boolean isValidInput = false;
        do {
            System.out.println(textToShow);
            String entrada = scanner.next();
            try {
                isValidInput = isValidDate(entrada, formatter);
                localDate = LocalDate.parse(entrada, formatter);
            } catch (FechaIngresadaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        } while (!isValidInput);
        return localDate;
    }

    public static String solicitarString() {
        return scanner.next();
    }

    public static int solicitarInt(String textToShow) {
        int entradaInt = 0;
        boolean isValidInput = false;
        do {
            System.out.println(textToShow);
            String entrada = scanner.next();
            try {
                isValidInput = isValidInt(entrada);
                entradaInt = Integer.parseInt(entrada);
            } catch (IdGastoIngresadoInvalidoException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } while (!isValidInput);
        return entradaInt;
    }

    public static void cerrarScanner() {
        ScannerInput.scanner.close();
    }
}
