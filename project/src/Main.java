import java.util.Scanner;

public class Main {
    private static User[] accounts = new User[50];
    private static Scanner sc = new Scanner(System.in);
    private static int count = 0;
    private static int id = 1000;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n(1)Open an account: \n" +
                    "(2)Deposit money:   \n" +
                    "(3)Withdraw money:   \n" +
                    "(4)View balance:  \n" +
                    "(5)Exit: \n");

            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 5) {
                System.out.println("System Exited");
                break;
            }

            switch (choice) {
                case 1:
                    openAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    viewBalance();
                    break;
                    case 5:
                        break;
                default:
                    System.out.println("Invalid choice!");

            }
        }
    }

    private static void openAccount() {
        if (count >= 10) {
            System.out.println("Maximum Accounts Reached");
            return;
        }

        System.out.print("Enter account name: ");
        String name = sc.nextLine();

        System.out.print("Enter account password: ");
        int password = sc.nextInt();
        sc.nextLine();

        accounts[count] = new User(name, id++, password);
        System.out.println("Account created. Your Account ID is " + (id - 1));
        count++;
    }

    private static User findAccount(int accountId) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getId() == accountId) {
                return accounts[i];
            }
        }
        return null;
    }

    private static void depositMoney() {
        if (count == 0) {
            System.out.println("No accounts exist");
            return;
        }

        System.out.print("Enter account ID: ");
        int accountId = sc.nextInt();

        User account = findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter account password: ");
        int password = sc.nextInt();

        if (account.getPassword() != password) {
            System.out.println("Invalid credentials");
            return;
        }

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: " + account.getBalance());
    }

    private static void withdrawMoney() {
        if (count == 0) {
            System.out.println("No accounts exist");
            return;
        }

        System.out.print("Enter account ID: ");
        int accountId = sc.nextInt();

        User account = findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter password: ");
        int password = sc.nextInt();

        if (account.getPassword() != password) {
            System.out.println("Invalid credentials");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance");
        }
    }

    private static void viewBalance() {
        if (count == 0) {
            System.out.println("No accounts exist");
            return;
        }

        System.out.print("Enter account ID: ");
        int accountId = sc.nextInt();

        User account = findAccount(accountId);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter password: ");
        int password = sc.nextInt();

        if (account.getPassword() != password) {
            System.out.println("Invalid credentials");
            return;
        }

        account.printBalance();
    }
}