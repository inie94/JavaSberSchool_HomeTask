import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.anani.lesson19.dto.DTOService;
import ru.anani.lesson19.dto.dtos.DishDTO;
import ru.anani.lesson19.services.DishService;
import ru.anani.lesson19.services.IngredientService;
import ru.anani.lesson19.services.ProductService;
import ru.anani.lesson19.services.UnitService;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class TestDB {
    @Autowired
    private DishService dishService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UnitService unitService;


    @Test
    @Transactional
    public void saveClearDish() {

    }

    @Test
    @Transactional
    public void search() {
        Set<DishDTO> dtoSet = new HashSet<>();
        dishService.searchByDishName("Cae").forEach(dish -> {
            dtoSet.add(DTOService.toDishDTO(dish));
        });
        System.out.println(dtoSet);
    }
}
