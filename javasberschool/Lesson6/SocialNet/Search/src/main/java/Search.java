import ru.anani.lesson6.socialnet.domain.User;
import ru.anani.lesson6.socialnet.persistence.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Search {
    private static final UserService service = new UserService();

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Write some request for search users: ");
            while (true) {
                if (reader.ready()) {
                    String request = "";
                    if (!(request = reader.readLine()).equals("exit")) {
                        List<User> userList = new ArrayList<>();
                        String finalRequest = request;
                        if (request.matches("\\d+")) {
                            service.getAllUsers().forEach(user -> {
                                if (String.valueOf(user.getId()).contains(finalRequest)) {
                                    userList.add(user);
                                }
                            });
                        } else {
                            service.getAllUsers().forEach(user -> {
                                if ((user.getFirstname()).contains(finalRequest)) {
                                    userList.add(user);
                                }
                                if ((user.getLastname()).contains(finalRequest) && !userList.contains(user)) {
                                    userList.add(user);
                                }
                                if ((user.getEmail()).contains(finalRequest) && !userList.contains(user)) {
                                    userList.add(user);
                                }
                            });
                        }
                        userList.forEach(System.out::println);
                        System.out.print("\nWrite some request for search users: ");
                    } else break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
