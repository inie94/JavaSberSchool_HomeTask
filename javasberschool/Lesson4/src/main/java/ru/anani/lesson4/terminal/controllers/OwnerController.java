package ru.anani.lesson4.terminal.controllers;

import ru.anani.lesson4.terminal.exeptions.IllegalAccountNumber;
import ru.anani.lesson4.terminal.exeptions.IllegalRequest;
import ru.anani.lesson4.terminal.exeptions.IllegalValue;
import ru.anani.lesson4.terminal.exeptions.IllegalValueForTake;
import ru.anani.lesson4.terminal.models.BankAccount;
import ru.anani.lesson4.terminal.models.Owner;
import ru.anani.lesson4.terminal.models.enums.Operation;
import ru.anani.lesson4.terminal.services.ServerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class OwnerController {


    private final BufferedReader reader;
    private final Owner owner;

    private BankAccount account;

    public OwnerController(BufferedReader reader, Owner owner) {
        this.reader = reader;
        this.owner = owner;
    }

    public Operation getRequest() {
        while (true) {
            try {
                if (reader.ready()) {
                    String request = reader.readLine();
                    if (Arrays.stream(Operation.values()).noneMatch(operation -> operation.name().equals(request))) {
                        throw new IllegalRequest();
                    }
                    if (request.equals(Operation.LOGOUT.name()) || request.equals(Operation.RETURN.name())) {
//                        PinValidator.clearPin();
                        return Operation.LOGOUT;
                    }
                    if (request.equals(Operation.EXIT.name())) {
                        return Operation.EXIT;
                    }
                    commandExecution(Operation.valueOf(request));
                    commandList();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalRequest illegalRequest) {
                System.out.print("Wrong request. Please write request again: ");
            }
        }
    }

    private void commandExecution(Operation cmd) {
        try {
            switch (cmd) {
                case BALANCE:
                    ServerService.showAListOfAccounts(owner);
                    System.out.print("Write account number for balance check: ");
                    account = getBankAccount();
                    if (account == null)
                        return;
                    ServerService.balanceCheck(account);
                    break;
                case TAKE_OFF:
                    ServerService.showAListOfAccounts(owner);
                    System.out.print("Write an account to withdraw funds: ");
                    account = getBankAccount();
                    if (account == null)
                        return;
                    System.out.print("Write how much money you want take: ");
                    Long value = valueValidation(Operation.TAKE_OFF);
                    if (value == null)
                        return;
                    ServerService.takeOfMoney(account, value);
                    ServerService.balanceCheck(account);
                    break;
                case MAKE:
                    ServerService.showAListOfAccounts(owner);
                    System.out.print("Write an account to deposit funds: ");
                    account = getBankAccount();
                    if (account == null)
                        return;
                    System.out.print("Write how much money you want to deposit: ");
                    value = valueValidation(Operation.MAKE);
                    if (value == null)
                        return;
                    ServerService.depositMoney(account, value);
                    ServerService.balanceCheck(account);
                    break;
                case OWNER_INFO:
                    ServerService.showOwnerInformation(owner);
                    break;
                case LOGOUT:
                    System.out.println("You are logout \n");
                    break;
                case RETURN:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BankAccount getBankAccount() throws IOException {
        String request = reader.readLine();
        if (request.equals(Operation.RETURN.name()))
            return null;
        try {
            if (owner.getAccounts().stream().noneMatch(account -> request.equals(account.getNumber()))) {
                throw new IllegalAccountNumber();
            }
            return owner.getAccounts().stream().filter(account -> request.equals(account.getNumber())).findFirst().get();

        } catch (IllegalAccountNumber illegalAccountNumber) {
            System.out.print("Illegal account number.\n" +
                    "Please write account number again: ");
            return getBankAccount();
        }
    }

    private Long valueValidation(Operation operation) throws IOException {
        String request = reader.readLine();
        if (request.equals(Operation.RETURN.name()))
            return null;
        try {
        long value = Long.parseLong(request);
        if (value % 100 != 0)
                throw new IllegalValue();
        if (value > account.getValue() && operation.equals(Operation.TAKE_OFF))
            throw new IllegalValueForTake();
        return value;
        }  catch (IllegalValueForTake illegalValueForTake) {
            System.out.println("Illegal money value for taking.\n" +
                    "Please write value again:");
            return valueValidation(operation);
        } catch (Exception e) {
            System.out.println("Illegal money value. Value must be a multiple on 100 money units. \n" +
                    "Please write value again:");
            return valueValidation(operation);
        }
    }

    public static void commandList() {
        System.out.println();
        Arrays.stream(Operation.values()).forEach(System.out::println);
        System.out.print("Write request name: ");
    }

}
