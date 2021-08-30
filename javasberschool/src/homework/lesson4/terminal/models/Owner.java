package homework.lesson4.terminal.models;

import java.util.List;
import java.util.Objects;

public class Owner {
    private long id;
    private String pinCode;
    private String firstname;
    private String lastname;
    private List<BankAccount> accounts;

    public Owner() {
    }

    public Owner(long id, String pinCode, String firstname, String lastname, List<BankAccount> accounts) {
        this.id = id;
        this.pinCode = pinCode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accounts = accounts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<BankAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id && Objects.equals(pinCode, owner.pinCode) && Objects.equals(firstname, owner.firstname) && Objects.equals(lastname, owner.lastname) && Objects.equals(accounts, owner.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pinCode, firstname, lastname, accounts);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", pinCode='" + pinCode + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
