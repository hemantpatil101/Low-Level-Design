package SplitwiseApp;
import java.util.*;

public class SplitwiseApp {
    public static void main(String[] args) {
        User u1 = new User("1", "Alice");
        User u2 = new User("2", "Bob");
        User u3 = new User("3", "Charlie");

        ExpenseManager manager = new ExpenseManager();
        Map<User, Double> splits = new HashMap<>();
        splits.put(u2, 50.0);
        splits.put(u3, 50.0);

        manager.addExpense(u1, 100, splits);
        manager.showBalances();

        // Settling debt
        manager.settleDebt(u2, u1, 30.0);
        manager.showBalances();
    }
}

class User {
    String id;
    String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Expense {
    String id;
    User paidBy;
    double amount;
    Map<User, Double> splits;

    public Expense(String id, User paidBy, double amount, Map<User, Double> splits) {
        this.id = id;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }
}

class ExpenseManager {
    List<Expense> expenses = new ArrayList<>();
    Map<User, Map<User, Double>> debtLedger = new HashMap<>();

    public void addExpense(User paidBy, double amount, Map<User, Double> splits) {
        String expenseId = UUID.randomUUID().toString();
        Expense expense = new Expense(expenseId, paidBy, amount, splits);
        expenses.add(expense);

        for (Map.Entry<User, Double> entry : splits.entrySet()) {
            User owedBy = entry.getKey();
            double share = entry.getValue();

            if (!debtLedger.containsKey(owedBy)) {
                debtLedger.put(owedBy, new HashMap<>());
            }
            debtLedger.get(owedBy).put(paidBy, debtLedger.get(owedBy).getOrDefault(paidBy, 0.0) + share);
        }
    }

    public void settleDebt(User payer, User receiver, double amount) {
        if (debtLedger.containsKey(payer) && debtLedger.get(payer).containsKey(receiver)) {
            double currentDebt = debtLedger.get(payer).get(receiver);
            double newDebt = Math.max(0, currentDebt - amount);
            debtLedger.get(payer).put(receiver, newDebt);
            if (newDebt == 0) {
                debtLedger.get(payer).remove(receiver);
            }
            System.out.println(payer.name + " paid " + receiver.name + " $" + amount + " to settle debt.");
        } else {
            System.out.println("No debt found between " + payer.name + " and " + receiver.name);
        }
    }

    public void showBalances() {
        for (Map.Entry<User, Map<User, Double>> entry : debtLedger.entrySet()) {
            User owedBy = entry.getKey();
            for (Map.Entry<User, Double> debt : entry.getValue().entrySet()) {
                User owedTo = debt.getKey();
                double amount = debt.getValue();
                if (amount > 0) {
                    System.out.println(owedBy.name + " owes " + owedTo.name + " $" + amount);
                }
            }
        }
    }
}


