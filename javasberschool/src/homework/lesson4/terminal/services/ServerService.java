package homework.lesson4.terminal.services;

import homework.lesson4.terminal.models.BankAccount;
import homework.lesson4.terminal.models.Owner;

public class ServerService {

    public static void takeOfMoney(BankAccount account, long value) {
        account.setValue(account.getValue() - value);
        System.out.println();
    }

    public static void depositMoney(BankAccount account, long value) {
        account.setValue(account.getValue() + value);
        System.out.println();
    }

    public static void showAccountInformation(BankAccount account) {
        System.out.println("Account number: " + account.getNumber());
        System.out.println("Account status: " + account.getStatus());
        System.out.println("Value of money: " + account.getValue() + " " + account.getCurrency().name());
        System.out.println("-------------------------------------------------------");
    }

    public static void showAListOfAccounts(Owner owner) {
        owner.getAccounts().forEach(ServerService::showAccountInformation);
    }

    public static void showOwnerInformation(Owner owner) {
        System.out.println("-------------------OWNER INFORMATION-------------------");
        System.out.println("Owner id: " + owner.getId());
        System.out.println("Owner firstname: " + owner.getFirstname());
        System.out.println("Owner lastname: " + owner.getLastname());
        System.out.println("---------------------BANK ACCOUNTS---------------------");
        showAListOfAccounts(owner);
    }

    public static void balanceCheck(BankAccount account) {
        System.out.println("on account No.: " + account.getNumber() + " " + account.getValue() + " " +
                account.getCurrency());
        System.out.println();
    }
}
