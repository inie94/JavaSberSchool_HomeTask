package ru.anani.lesson4.terminal.controllers;

import ru.anani.lesson4.terminal.models.Owner;
import ru.anani.lesson4.terminal.services.PinValidator;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginController {
    private final PinValidator validator = new PinValidator();

    private final BufferedReader reader;

    public LoginController(BufferedReader reader) {
        this.reader = reader;
    }

    public Owner authorize() throws IOException {
            validator.setPin("");
            System.out.println("Hello!! We are glad to see you!");
            System.out.println("Write pin code: ");
            while (true) {
                try {
                    if (reader.ready()) {
                        String num = reader.readLine();
                        if (num.length() > 1)
                            throw new NumberFormatException();
                        switch (validator.checkPinNum(num)) {
                            case AUTHORIZED:
                                return validator.getOwner();
                            case AUTHORIZE_ERROR:
                                return null;
                        }
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Wrong input size. Please write symbol again :");
                }
            }
    }
}
