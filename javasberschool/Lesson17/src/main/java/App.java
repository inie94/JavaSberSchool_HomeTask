import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.inie.lesson17.AnimalService;
import ru.inie.lesson17.Config;


public class App {

    @Autowired
    private static AnimalService animalService;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        animalService = context.getBean(AnimalService.class);
        animalService.voice();
    }

}
