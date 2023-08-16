package henryproyectointegrador.utils;

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
}