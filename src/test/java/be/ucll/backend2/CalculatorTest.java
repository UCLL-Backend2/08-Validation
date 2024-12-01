package be.ucll.backend2;

import be.ucll.backend2.service.CalculatorService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorTest {
    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void whenFactorialIsCalledWithPositiveNumber_thenFactorialIsCalculatedCorrectly() {
        final var result = calculatorService.factorial(3);

        Assertions.assertEquals(6L, result);
    }

    @Test
    public void whenFactorialIsCalledWithNegativeNumber_thenExceptionIsThrown() {
        final var exception = Assertions.assertThrows(
                ValidationException.class,
                () -> calculatorService.factorial(-1));

        Assertions.assertEquals("factorial.n: must be greater than or equal to 0", exception.getMessage());
    }
}
