package homework.lesson4.terminal.services;

import homework.lesson4.terminal.models.AuthorizeStatus;

public class PinValidator {
    private String pin = "";

    public AuthorizeStatus checkPinNum(String pinNum) {
        try {
            if(pinNum.length() > 1)
                throw new Exception();
            int num = Integer.parseInt(pinNum);
            pin += num;
            if (pin.length() == 4) {
                return AuthorizeStatus.AUTHORIZED;
            }
        } catch (Exception exception) {
            System.out.println("Wrong symbol");
        }
        return AuthorizeStatus.NON_AUTHORIZED;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
