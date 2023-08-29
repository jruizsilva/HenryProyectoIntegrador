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
    }
}