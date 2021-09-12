import org.junit.Test;

import java.util.Base64;

public class test {
    @Test
    public void testStingNull() {
        System.out.println(Base64.getEncoder().encodeToString(null));
    }
}
