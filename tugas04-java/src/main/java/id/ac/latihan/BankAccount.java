package id.ac.latihan;

public class BankAccount {
    private double balance = 0.0;

    private void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount < 0) {
            throw new IllegalArgumentException(
                "Amount must be finite and non-negative");
        }
    }

    public void deposit(double amount) {
        validateAmount(amount);
        double newBalance = balance + amount;
        if (!Double.isFinite(newBalance)) {
            throw new IllegalArgumentException("Balance overflow");
        }
        balance = newBalance;
    }

    public void withdraw(double amount) {
        validateAmount(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
