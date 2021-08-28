package homework.lesson4.terminal.models;

public class BankAccount {
    private long ownerId;
    private String number;
    private long value;
    private StatusBankAccount status;
    private Currency currency;

    public BankAccount() {
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public StatusBankAccount getStatus() {
        return status;
    }

    public void setStatus(StatusBankAccount status) {
        this.status = status;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
