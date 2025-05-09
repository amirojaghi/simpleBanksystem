public class Account {
    private String firstName;
    private String lastName;
    private int id;
    private int pin;
    private double rialBalance;
    private double dollarBalance;
    private static final double EXCHANGE_RATE = 80.0;

    public Account(String firstName, String lastName, int pin, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.id = id;
        this.rialBalance = 0;
        this.dollarBalance = 0;
    }

    public int getId() {
        return id;
    }

    public boolean checkPin(int inputPin) {
        return this.pin == inputPin;
    }

    public void depositRial(double amount) {
        if (amount > 0) {
            rialBalance += amount;
            System.out.println(amount + " Rial deposited.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void depositDollar(double amount) {
        if (amount > 0) {
            dollarBalance += amount;
            System.out.println(amount + " USD deposited.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public boolean withdrawRial(double amount) {
        if (amount <= rialBalance) {
            rialBalance -= amount;
            return true;
        }
        return false;
    }

    public boolean withdrawDollar(double amount) {
        if (amount <= dollarBalance) {
            dollarBalance -= amount;
            return true;
        }
        return false;
    }

    public void showInfo() {
        System.out.println("\n--- Account Information ---");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Account ID: " + id);
        System.out.println("Rial Balance: " + rialBalance);
        System.out.println("USD Balance: $" + dollarBalance);
        System.out.println("Total in Rial: " + (rialBalance + dollarBalance * EXCHANGE_RATE));
        System.out.println("Total in USD: $" + (dollarBalance + rialBalance / EXCHANGE_RATE));
    }
}
