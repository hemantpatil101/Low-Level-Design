package ATMApp;

import java.util.*;

public class ATMSystem {
    public static void main(String[] args) {
        Bank bank = new HDFCBank();
        bank.addAccount(new Account("123456", "4321", 1000.0));

        Card card = new Card("123456", bank);
        ATM atm = new ATM();
        atm.insertCard(card);
    }
}

class ATM {
    private Scanner scanner = new Scanner(System.in);
    private Card insertedCard;
    private boolean isAuthenticated = false;

    public void insertCard(Card card) {
        this.insertedCard = card;
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        if (card.authenticate(pin)) {
            isAuthenticated = true;
            System.out.println("Authentication successful.");
            showMenu();
        } else {
            System.out.println("Invalid PIN. Card ejected.");
            ejectCard();
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Eject Card");
            System.out.print("Choose option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> {
                    double balance = insertedCard.getBank().checkBalance(insertedCard.getAccountNumber());
                    System.out.println("Balance: $" + balance);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amt = scanner.nextDouble();
                    boolean success = insertedCard.getBank().withdraw(insertedCard.getAccountNumber(), amt);
                    if (success) System.out.println("Withdrawn: $" + amt);
                    else System.out.println("Insufficient balance.");
                }
                case 3 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amt = scanner.nextDouble();
                    insertedCard.getBank().deposit(insertedCard.getAccountNumber(), amt);
                    System.out.println("Deposited: $" + amt);
                }
                case 4 -> {
                    ejectCard();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void ejectCard() {
        System.out.println("Card ejected.");
        insertedCard = null;
        isAuthenticated = false;
    }
}

class Card {
    private final String accountNumber;
    private final Bank bank;

    public Card(String accountNumber, Bank bank) {
        this.accountNumber = accountNumber;
        this.bank = bank;
    }

    public boolean authenticate(String pin) {
        return bank.validatePin(accountNumber, pin);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Bank getBank() {
        return bank;
    }
}

interface Bank {
    boolean validatePin(String accountNumber, String pin);
    double checkBalance(String accountNumber);
    boolean withdraw(String accountNumber, double amount);
    void deposit(String accountNumber, double amount);
    void addAccount(Account account);
}

class HDFCBank implements Bank {
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public boolean validatePin(String accountNumber, String pin) {
        Account acc = accounts.get(accountNumber);
        return acc != null && acc.validatePin(pin);
    }

    public double checkBalance(String accountNumber) {
        return accounts.get(accountNumber).getBalance();
    }

    public boolean withdraw(String accountNumber, double amount) {
        return accounts.get(accountNumber).withdraw(amount);
    }

    public void deposit(String accountNumber, double amount) {
        accounts.get(accountNumber).deposit(amount);
    }
}

class Account {
    private final String accountNumber;
    private final String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(String inputPin) {
        return pin.equals(inputPin);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}
