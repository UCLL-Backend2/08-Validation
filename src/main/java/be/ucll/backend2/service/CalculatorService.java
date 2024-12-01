package be.ucll.backend2.service;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CalculatorService {
    public long factorial(@PositiveOrZero int n) {
        if (n == 0) {
            return 1L;
        } else {
            return n * factorial(n - 1);
        }
    }
}
