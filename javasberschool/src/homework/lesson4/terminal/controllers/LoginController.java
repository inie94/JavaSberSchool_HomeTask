package homework.lesson4.terminal.controllers;

import homework.lesson4.terminal.exeptions.AccountIsLockedException;
import homework.lesson4.terminal.models.Owner;
import homework.lesson4.terminal.services.PinValidator;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginController {
    private final PinValidator validator = new PinValidator();

    private final BufferedReader reader;

    public LoginController(BufferedReader reader) {
        this.reader = reader;
    }

    public Owner authorize() throws IOException {
        try {
            System.out.println("Hello!! We are glad to see you!");
            System.out.println("Write pin code: ");
            while (true) {
                if (reader.ready()) {
                    switch (validator.checkPinNum(reader.readLine())) {
                        case AUTHORIZED:
                            return validator.getOwner();
                        case AUTHORIZE_ERROR:
                            throw new AccountIsLockedException();
                    }
                }
            }
        } catch (AccountIsLockedException e) {
            System.out.println("Owner account is blocked");
        }
        return null;
    }
}
