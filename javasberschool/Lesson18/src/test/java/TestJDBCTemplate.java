import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.anani.lesson18.config.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
//@TestPropertySource("/test.properties")
public class TestJDBCTemplate {

    @Autowired
//    private DishTemplate dishTemplate;

    @Test
    public void getDish() {
//        dishTemplate.getDishByName("Caesar salad");
    }
}
