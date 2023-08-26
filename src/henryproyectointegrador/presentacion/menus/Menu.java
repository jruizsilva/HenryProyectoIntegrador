package henryproyectointegrador.presentacion.menus;

import java.util.Map;

import static henryproyectointegrador.utils.ScannerInput.requestStringInput;
import static henryproyectointegrador.utils.ScannerValidator.validOption;

public class Menu {
    private final Map<String, String> validOptions;
    private String input;
    private Boolean inputValid;

    public Menu() {
        validOptions = null;
    }

    public Menu(Map<String, String> validOptions) {
        this.validOptions = validOptions;
    }

    public Menu initMenu() {
        return this;
    }

    public String requestInput() {
        if (validOptions == null) {
            throw new NullPointerException("Valid options not defined");
        }
        do {
            showMenuOptions();
            System.out.println("Seleccione una opción: ");
            input = requestStringInput();
            if (validOption(input, validOptions.keySet()
                                               .toArray(new String[0]))) {
                inputValid = true;
            }

            if (!inputValid)
                System.out.printf("\nOpcion \"%s\" no valida, intente de nuevo.\n", input);
        } while (!input.equals("5") && !inputValid);
        return input;
    }

    private void showMenuOptions() {
        if (validOptions == null) {
            throw new NullPointerException("Valid options not defined");
        }
        for (String key : validOptions.keySet()) {
            System.out.println(validOptions.get(key));
        }
    }

    public String showMenu() {
        if (validOptions == null) {
            throw new NullPointerException("Valid options not defined");
        }
        String option;
        boolean inputValido = false;
        do {
            showMenuOptions();
            System.out.println("Seleccione una opción: ");
            option = requestStringInput();
            if (validOption(option, validOptions.keySet()
                                                .toArray(new String[0]))) {
                inputValido = true;
            }

            if (!inputValido)
                System.out.printf("\nOpcion \"%s\" no valida, intente de nuevo.\n", option);
        } while (!option.equals("5") && !inputValido);
        return option;
    }
}
