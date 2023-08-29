package henryproyectointegrador.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static henryproyectointegrador.utils.ScannerValidator.validOption;

class ScannerValidatorTest {
    @Nested
    class ValidOptionTest {
        @Test
        void should_ReturnFalse_When_OptionIsNotValid() {
            String[] validOptions = {"1", "2"};
            String option = "3";

            boolean result = validOption(option, validOptions);

            Assertions.assertFalse(result);
        }

        @Test
        void should_ReturnTrue_When_OptionIsValid() {
            String[] validOptions = {"1", "2", "3", "4", "5"};
            String option = "5";

            boolean result = validOption(option, validOptions);

            Assertions.assertTrue(result);
        }

        @Test
        void should_ReturnException_When_OptionIsNull() {
            String[] validOptions = {"1", "2", "3", "4", "5"};

            Assertions.assertThrows(IllegalArgumentException.class, () -> validOption(null, validOptions), "Debe lanzar IllegalArgumentException exception");
        }

        @Test
        void should_ReturnException_When_ValidOptionsIsNull() {
            String option = "5";

            Assertions.assertThrows(IllegalArgumentException.class, () -> validOption(option, null), "Debe lanzar IllegalArgumentException exception");
        }
    }

    @Nested
    class ValidDoubleTest {
    }
}