package homework.lesson4.terminal.controllers;

import homework.lesson4.terminal.models.AuthorizeStatus;
import homework.lesson4.terminal.services.PinValidator;
import homework.lesson4.terminal.services.ServerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginController {
    private final PinValidator validator = new PinValidator();
    private final ServerService server = new ServerService();

    public void authorize() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                if(reader.ready()) {
                    if (validator.checkPinNum(reader.readLine()) == AuthorizeStatus.AUTHORIZED) {
                        // прошла авторизация
                    }
                }
            }
        }
    }
}
