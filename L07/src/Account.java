public class Account implements Moodable, Comparable<Account> {
    private int amount;

    public Account(int amount) {
        this.amount = amount;
    }

    public Account(int x, int y) {
        this.amount = x + y;

    }

    public Account() {
        this(0, 0);
    }

    public int getAmount() {
        return amount;
    }

    public void transact(Account other, int transactionAmount) {
        if (transactionAmount <= 0) {
            throw new IllegalArgumentException("Invalid amount detected: " + String.valueOf(transactionAmount));
        }
        if (amount < transactionAmount) {
            throw new IllegalArgumentException("Not enough money: " + String.valueOf(transactionAmount));
        }
        amount -= transactionAmount;
        other.amount += transactionAmount;
    }

    @Override
    public boolean getMood() {
        return amount >= 100000;
    }

    @Override
    public int compareTo(Account o) {
        return amount - o.amount;
    }
}
