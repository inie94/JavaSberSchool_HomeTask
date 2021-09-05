package ru.anani.lesson4.terminal.services;

import ru.anani.lesson4.terminal.models.BankAccount;
import ru.anani.lesson4.terminal.models.enums.Currency;
import ru.anani.lesson4.terminal.models.Owner;
import ru.anani.lesson4.terminal.models.enums.StatusBankAccount;

import java.util.*;

public class DataBase {

    private static List<Owner> owners = new ArrayList<>();

    private BankAccount lastAccount;


    private static final List<StatusBankAccount> STATUS_BANK_ACCOUNTS =
            Collections.unmodifiableList(
                    Arrays.asList(StatusBankAccount.CREDIT_ACCOUNT, StatusBankAccount.DEBIT_ACCOUNT)
            );
    private static final List<Currency> CURRENCIES =
            Collections.unmodifiableList(
                    Arrays.asList(Currency.EUR, Currency.RUB, Currency.USD)
            );

    private static final List<String> FIRSTNAMES =
            Collections.unmodifiableList(
                    Arrays.asList("Jhon", "Josef", "Ien", "Michal")
            );

    private static final List<String> LASTNAMES =
            Collections.unmodifiableList(
                    Arrays.asList("Smith", "Berger", "Peters", "Nixon")
            );

    private static final Random RANDOM = new Random();


    {
        int pinNumber = 0;
        for (int i = 0; i < 5; i++) {
            StringBuilder pin = new StringBuilder(String.valueOf(++pinNumber));
            while (pin.length() < 4)
                pin.insert(0, 0);
            Owner owner = new Owner();
            owner.setId(i + 1);
            owner.setPinCode(pin.toString());
            owner.setFirstname(FIRSTNAMES.get(RANDOM.nextInt(FIRSTNAMES.size())));
            owner.setLastname(LASTNAMES.get(RANDOM.nextInt(LASTNAMES.size())));
            owner.setAccounts(
                    new ArrayList<>(
                            Arrays.asList(
                                    createNewBankAccount(owner),
                                    createNewBankAccount(owner),
                                    createNewBankAccount(owner)
                            )
                    )
            );
            System.out.println(owner);
            owners.add(owner);
        }

    }

    public BankAccount createNewBankAccount(Owner currentOwner) {
        BankAccount account;
        if (lastAccount == null) {
            account = new BankAccount(
                    currentOwner.getId(),
                    "0000000001",
                    100 + (long) (Math.random() * (1000000 - 100)),
                    STATUS_BANK_ACCOUNTS.get(RANDOM.nextInt(STATUS_BANK_ACCOUNTS.size())),
                    CURRENCIES.get(RANDOM.nextInt(CURRENCIES.size())));
        }
        else {
            account = new BankAccount(
                    currentOwner.getId(),
                    nextBankAccountNumber(lastAccount),
                    100 + (long) (Math.random() * (1000000 - 100)),
                    STATUS_BANK_ACCOUNTS.get(RANDOM.nextInt(STATUS_BANK_ACCOUNTS.size())),
                    CURRENCIES.get(RANDOM.nextInt(CURRENCIES.size())));
        }
        lastAccount = account;
        System.out.println(account);
        return account;
    }

    public String nextBankAccountNumber(BankAccount account) {
        StringBuilder result;
        int number = Integer.parseInt(account.getNumber()) + 1;
        result = new StringBuilder(String.valueOf(number));
        while (result.length() < 10) {
            result.insert(0, "0");
        }
        System.out.println("nextBankAccountNumber: " + result);
        return result.toString();
    }

    public static List<Owner> getOwners() {
        return owners;
    }

    public static void setOwners(List<Owner> owners) {
        DataBase.owners = owners;
    }

}
