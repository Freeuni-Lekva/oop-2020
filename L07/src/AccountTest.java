import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void sort() {
        List<Account> accs = new ArrayList<>();
        accs.add(new Account(5));
        accs.add(new Account(10));
        accs.add(new Account(7));
        accs.add(new Account(1));
        Collections.sort(accs);
        assertEquals(1, accs.get(0).getAmount());
        assertEquals(5, accs.get(1).getAmount());
        assertEquals(7, accs.get(2).getAmount());
        assertEquals(10, accs.get(3).getAmount());
        Collections.sort(accs, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o2.getAmount() - o1.getAmount();
            }
        });
        assertEquals(1, accs.get(3).getAmount());
        assertEquals(5, accs.get(2).getAmount());
        assertEquals(7, accs.get(1).getAmount());
        assertEquals(10, accs.get(0).getAmount());
    }

    public void foo() {
        List<Moodable> l = new ArrayList<Account>();
        Moodable[] arr = new Account[5];
    }
}