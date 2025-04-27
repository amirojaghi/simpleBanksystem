public class User {
    private String name;
    private double balance;
    private final int id;
    private final int password;

    public User(String name, int id, int password) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.balance = 0;
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public int getId() { return id; }
    public int getPassword() { return password; }

    public void setName(String name) { this.name = name; }
    public void setBalance(double balance) { this.balance = balance; }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public void printBalance() {
        System.out.printf("Name: %s, ID: %d, Balance: %.2f\n", name, id, balance);
    }
}