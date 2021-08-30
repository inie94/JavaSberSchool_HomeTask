package homework.lesson4.terminal.models;

import homework.lesson4.terminal.models.enums.Currency;
import homework.lesson4.terminal.models.enums.StatusBankAccount;

import java.util.Objects;

public class BankAccount {
    private long ownerId;
    private String number;
    private long value;
    private StatusBankAccount status;
    private Currency currency;

    public BankAccount() {

    }

    public BankAccount(long ownerId, String number, long value, StatusBankAccount status, Currency currency) {
        this.ownerId = ownerId;
        this.number = number;
        this.value = value;
        this.status = status;
        this.currency = currency;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount account = (BankAccount) o;
        return ownerId == account.ownerId && value == account.value && Objects.equals(number, account.number) && status == account.status && currency == account.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, number, value, status, currency);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "ownerId=" + ownerId +
                ", number='" + number + '\'' +
                ", value=" + value +
                ", status=" + status +
                ", currency=" + currency +
                '}';
    }
}
