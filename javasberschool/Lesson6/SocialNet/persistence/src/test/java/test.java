import org.junit.Assert;
import org.junit.Test;
import ru.anani.lesson6.socialnet.domain.User;
import ru.anani.lesson6.socialnet.persistence.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class test {

    private final UserService service = new UserService();

    @Test
    public void getAllUsers() {
        List<User> actual = new ArrayList<>(Arrays.asList(
                new User(1L, "user1@mail.com", "qwerty", "Jhon", "James", "Male", new Date(1684654865)),
                new User(2L, "user2@mail.com", "qwerty", "Ellie", "Smith", "Male", new Date(1684654865)),
                new User(3L, "user3@mail.com", "qwerty", "Max", "Dalton", "Male", new Date(1684654865)),
                new User(4L, "user4@mail.com", "qwerty", "Marcus", "Michelson", "Male", new Date(1684654865)),
                new User(5L, "user5@mail.com", "qwerty", "Lion", "Jarvis", "Male", new Date(1684654865)),
                new User(11L, "user11@mail.com", "qwerty", "Lion", "Jarvis", "Male", new Date(1684654865))
        ));

        List<User> expected = service.getAllUsers();

        Assert.assertEquals(actual, expected);

    }

    @Test
    public void getUserById() {
        User actual = new User(11L, "user11@mail.com", "qwerty", "Lion", "Jarvis", "Male", new Date(1684654865));

        User expected = service.getUserById(11);

        Assert.assertEquals(actual, expected);

        expected = service.getUserById(13);

        Assert.assertEquals(expected, null);
    }


}
