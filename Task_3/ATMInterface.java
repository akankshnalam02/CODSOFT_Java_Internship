import java.util.Scanner;

// Class to represent a user's bank account
class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("✅ ₹%.2f deposited successfully!\n", amount);
        } else {
            System.out.println("⚠️ Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("⚠️ Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance! Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.printf("💸 ₹%.2f withdrawn successfully!\n", amount);
        }
    }

    public void checkBalance() {
        System.out.printf("💰 Current Balance: ₹%.2f\n", balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}

// Class to represent the ATM machine
public class ATMInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static BankAccount account;

    public static void main(String[] args) {
        System.out.println("🏧 Welcome to CODSOFT ATM Interface!");

        // Creating a sample account
        System.out.print("Enter your name to create an account: ");
        String name = scanner.nextLine();
        account = new BankAccount(name, 1000.00);  // Starting with ₹1000 balance
        System.out.println("✅ Account created for " + account.getAccountHolder() + " with ₹1000.00 balance.\n");

        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> handleDeposit();
                case 2 -> handleWithdrawal();
                case 3 -> account.checkBalance();
                case 4 -> {
                    System.out.println("🚪 Exiting ATM. Thank you!");
                    exit = true;
                }
                default -> System.out.println("⚠️ Invalid option. Please try again.");
            }

            if (!exit) {
                System.out.println("\n------------------------------\n");
            }
        }
    }

    private static void showMenu() {
        System.out.println("🔘 Choose an option:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice (1-4): ");
    }

    private static int getUserChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.print("⚠️ Enter a number between 1 and 4: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Invalid input. Please enter a number: ");
            }
        }
    }

    private static void handleDeposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = getValidAmount();
        account.deposit(amount);
    }

    private static void handleWithdrawal() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = getValidAmount();
        account.withdraw(amount);
    }

    private static double getValidAmount() {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount > 0) {
                    return amount;
                } else {
                    System.out.print("⚠️ Amount must be greater than 0. Try again: ₹");
                }
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Invalid amount. Enter a number: ₹");
            }
        }
    }
}
