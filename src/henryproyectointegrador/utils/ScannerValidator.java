package henryproyectointegrador.utils;

import henryproyectointegrador.interfaces.IValidOption;

import java.util.Arrays;

public class ScannerValidator implements IValidOption {
    private static ScannerValidator instance;

    private ScannerValidator() {
    }

    public static ScannerValidator getInstance() {
        if (ScannerValidator.instance == null) {
            ScannerValidator.instance = new ScannerValidator();
        }
        return ScannerValidator.instance;
    }

    @Override
    public boolean validOption(String opcion, String[] opcionesValidas) {
        return Arrays.asList(opcionesValidas)
                     .contains(opcion);
    }
}