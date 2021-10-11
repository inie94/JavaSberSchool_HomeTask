import org.junit.Test;
import ru.anani.lesson16.CalculatorImpl;
import ru.anani.lesson16.cache.CacheProxy;

public class CacheProxyTest {

    @Test
    public void main(){
        CacheProxy cacheProxy = new CacheProxy();
        CalculatorImpl calculator = cacheProxy.cache(new CalculatorImpl());
        System.out.println(calculator.fibonacci(1));
    }
}
