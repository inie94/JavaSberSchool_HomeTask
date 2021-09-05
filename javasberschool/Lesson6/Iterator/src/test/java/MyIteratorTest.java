import org.junit.Assert;
import org.junit.Test;
import ru.anani.lesson6.iterator.MyIterator;

import java.util.NoSuchElementException;

public class MyIteratorTest {

    @Test
    public void hasNext() {
        Integer[] integers = new Integer[0];
        MyIterator<Integer> iterator = new MyIterator(integers);
        Assert.assertEquals(iterator.hasNext(), false);

        integers = new Integer[10];
        iterator = new MyIterator(integers);
        Assert.assertEquals(iterator.hasNext(), true);
    }

    @Test(expected = NoSuchElementException.class)
    public void next() {
        Integer[] integers = new Integer[0];
        MyIterator<Integer> iterator = new MyIterator(integers);
        Assert.assertEquals(new NoSuchElementException(), iterator.next());

        integers = new Integer[]{1, 2, 3};
        iterator = new MyIterator(integers);
        int i = 0;
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            Assert.assertEquals(integer, integers[i++]);
        }
        Assert.assertEquals(new NoSuchElementException(), iterator.next());
    }
}
