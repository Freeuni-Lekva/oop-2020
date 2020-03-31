import java.util.Comparator;

public class AccountDecreasingComparator implements Comparator<Account> {
    @Override
    public int compare(Account o1, Account o2) {
        return o2.getAmount() - o1.getAmount();
    }
}
