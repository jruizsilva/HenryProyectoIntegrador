package henryproyectointegrador.utils;

import henryproyectointegrador.excepciones.MontoIngresadoInvalidoException;

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
}