package henryproyectointegrador.utils;

import henryproyectointegrador.excepciones.FechaIngresadaInvalidaException;
import henryproyectointegrador.excepciones.IdGastoIngresadoInvalidoException;
import henryproyectointegrador.excepciones.MontoIngresadoInvalidoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ScannerValidator {
    private static ScannerValidator instance;

    private ScannerValidator() {
    }

    public synchronized static ScannerValidator getInstance() {
        if (ScannerValidator.instance == null) {
            ScannerValidator.instance = new ScannerValidator();
        }
        return ScannerValidator.instance;
    }

    public static boolean validOption(String opcion, String[] opcionesValidas) {
        return Arrays.asList(opcionesValidas)
                     .contains(opcion);
    }

    public static boolean isValidDouble(String entrada) throws MontoIngresadoInvalidoException {
        try {
            Double.parseDouble(entrada);
            return true;
        } catch (NumberFormatException e) {
            String msg = String.format("El dato ingresado \"%s\" no es valido. Intente nuevamente", entrada);
            throw new MontoIngresadoInvalidoException(msg);
        }
    }

    public static boolean isValidDate(String entrada, SimpleDateFormat format) throws FechaIngresadaInvalidaException {
        try {
            format.parse(entrada);
            return true;
        } catch (ParseException e) {
            String msg = String.format("El dato ingresado \"%s\" no es valido. Intente nuevamente", entrada);
            throw new FechaIngresadaInvalidaException(msg);
        }
    }

    public static boolean isValidDate(String entrada, DateTimeFormatter format) throws FechaIngresadaInvalidaException {
        try {
            format.parse(entrada);
            return true;
        } catch (Exception e) {
            String msg = String.format("El dato ingresado \"%s\" no es valido. Intente nuevamente", entrada);
            throw new FechaIngresadaInvalidaException(msg);
        }
    }

    public static boolean isValidInt(String entrada) throws IdGastoIngresadoInvalidoException {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (NumberFormatException e) {
            String msg = String.format("El dato ingresado \"%s\" no es valido. Intente nuevamente", entrada);
            throw new IdGastoIngresadoInvalidoException(msg);
        }
    }
}