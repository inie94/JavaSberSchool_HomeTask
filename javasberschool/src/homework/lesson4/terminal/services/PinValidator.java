package homework.lesson4.terminal.services;

import homework.lesson4.terminal.exeptions.AccountIsLockedException;
import homework.lesson4.terminal.models.AuthorizeStatus;
import homework.lesson4.terminal.models.Owner;

import java.util.Date;
import java.util.NoSuchElementException;

public class PinValidator {
    private String pin = "";
    private Owner owner = null;
    private AuthorizeStatus status = AuthorizeStatus.NON_AUTHORIZED;
    private Date endAccountBlocked;

    public AuthorizeStatus checkPinNum(String pinNum) throws NumberFormatException {
        try {
            int num = Integer.parseInt(pinNum);
            pin += num;
            if (status == AuthorizeStatus.AUTHORIZE_ERROR) {
                throw new AccountIsLockedException();
            }
            status = pinChecked(pin.length());
        } catch (AccountIsLockedException e) {
            Date date = new Date();
            if (date.after(endAccountBlocked)) {
                status = AuthorizeStatus.NON_AUTHORIZED;
                status = pinChecked(pin.length());
            } else {
                pin = "";
                System.out.println("Owner account is blocked for " +
                        ((endAccountBlocked.getTime() - date.getTime())/ 1000) + " seconds.");
            }
        }
        return status;
    }

    private AuthorizeStatus pinChecked(int pinLength) {
        try {
            if (pinLength == 4) {
                owner = DataBase.getOwners().stream().filter(o -> o.getPinCode().equals(pin)).findFirst().get();
                pin = "";
                status = AuthorizeStatus.AUTHORIZED;
                return status;
            } else {
                return AuthorizeStatus.NON_AUTHORIZED;
            }
        } catch (NoSuchElementException exception) {
            status = AuthorizeStatus.AUTHORIZE_ERROR;
            pin = "";
            endAccountBlocked = new Date(new Date().getTime() + 10000);
            System.out.println("Owner account is blocked for " + 10 + " seconds.");
        }
        return status;
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

    public void setStatus(AuthorizeStatus status) {
        this.status = status;
    }
}
