package homework.lesson4.terminal.services;

import homework.lesson4.terminal.models.AuthorizeStatus;
import homework.lesson4.terminal.models.Owner;

public class PinValidator {
    private String pin = "";
    private Owner owner = null;

    public AuthorizeStatus checkPinNum(String pinNum) {
        try {
            if(pinNum.length() > 1)
                throw new Exception();
            int num = Integer.parseInt(pinNum);
            pin += num;
            if (pin.length() == 4) {
                owner = DataBase.getOwners().stream().filter(o -> o.getPinCode().equals(pin)).findFirst().get();
                pin = "";
                if (owner == null)
                    return AuthorizeStatus.AUTHORIZE_ERROR;
                else
                    return AuthorizeStatus.AUTHORIZED;
            }
        } catch (Exception exception) {
            System.out.println("Wrong symbol");
        }
        return AuthorizeStatus.NON_AUTHORIZED;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
