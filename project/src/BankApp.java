import java.util.Scanner;

public class BankApp {

    private static final Account[] accounts = new Account[100];
    private static final Scanner scanner = new Scanner(System.in);
    private static int totalAccounts = 0;
    private static int nextId = 1000;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Account Info");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    showAccount();
                    break;
                case 5:
                    System.out.println("Thank you for using our service.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void createAccount() {
        if (totalAccounts >= accounts.length) {
            System.out.println("Account limit reached.");
            return;
        }

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a 4-digit PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        int id = nextId++;
        accounts[totalAccounts++] = new Account(firstName, lastName, pin, id);

        System.out.println("Account created successfully. Your Account ID is: " + id);
    }

    private static Account getAccount(int id) {
        for (int i = 0; i < totalAccounts; i++) {
            if (accounts[i].getId() == id) {
                return accounts[i];
            }
        }
        return null;
    }

    private static void deposit() {
        System.out.print("Enter your account ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Account acc = getAccount(id);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        if (!acc.checkPin(pin)) {
            System.out.println("Incorrect PIN.");
            return;
        }

        System.out.print("Currency (1 = Rial, 2 = USD): ");
        int currency = scanner.nextInt();

        System.out.print("Amount to deposit: ");
        double amount = scanner.nextDouble();

        if (currency == 1) {
            acc.depositRial(amount);
        } else if (currency == 2) {
            acc.depositDollar(amount);
        } else {
            System.out.println("Invalid currency choice.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter your account ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Account acc = getAccount(id);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        if (!acc.checkPin(pin)) {
            System.out.println("Incorrect PIN.");
            return;
        }

        System.out.print("Currency (Rial or USD): ");
        String currency = scanner.nextLine();

        System.out.print("Amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (currency.equalsIgnoreCase("Rial")) {
            if (acc.withdrawRial(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient Rial balance.");
            }
        } else if (currency.equalsIgnoreCase("USD")) {
            if (acc.withdrawDollar(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient USD balance.");
            }
        } else {
            System.out.println("Invalid currency.");
        }
    }

    private static void showAccount() {
        System.out.print("Enter your account ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Account acc = getAccount(id);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        if (acc.checkPin(pin)) {
            acc.showInfo();
        } else {
            System.out.println("Incorrect PIN.");
        }
    }
}
