import org.junit.Test;
import ru.anani.lesson16.CalculatorImpl;

import java.util.Arrays;
import java.util.List;

public class FibonacciTest {
    @Test(expected = RuntimeException.class)
    public void fibonacciException() {
        CalculatorImpl calculator = new CalculatorImpl();

        List<Integer> fibonacci = calculator.fibonacci(0);
    }

    @Test()
    public void fibonacci() {
        CalculatorImpl calculator = new CalculatorImpl();

        assert Arrays.asList(0).equals(calculator.fibonacci(1));

        assert Arrays.asList(0, 1).equals(calculator.fibonacci(2));

        assert Arrays.asList(0, 1, 1).equals(calculator.fibonacci(3));

        assert Arrays.asList(0, 1, 1, 2).equals(calculator.fibonacci(4));

        assert Arrays.asList(0, 1, 1, 2, 3).equals(calculator.fibonacci(5));

        assert Arrays.asList(0, 1, 1, 2, 3, 5).equals(calculator.fibonacci(6));

        assert Arrays.asList(0, 1, 1, 2, 3, 5, 8).equals(calculator.fibonacci(7));

        assert Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13).equals(calculator.fibonacci(8));
    }
}
