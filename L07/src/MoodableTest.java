import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoodableTest {

    @Test
    public void casting() {
        Moodable m = new Student(7);
        assertFalse(m.getMood());
    }

    @Test
    public void instanceofExample() {
        Moodable m = new Account(5);
        if (m instanceof Account) {
            Account acc = (Account) m;
            System.out.println(acc.getAmount());
        } else {
            System.out.print("Not Account");
        }
    }

    @Test
    public void collection() {
        Moodable[] moodables = new Moodable[4];
        moodables[0] = new Student(5);
        moodables[1] = new Grad(9, 11);
        moodables[2] = new Account();
        Account acc = new Account(1000000);
        moodables[3] = acc;
//        for (int i = 0; i < moodables.length; ++i) {
//            System.out.println(moodables[i].getMood());
//        }
        printMoods(moodables);
    }

    public void printMoods(Moodable[] moodables) {
        for (Moodable m : moodables) {
            System.out.println(m.getMood());
        }
    }
}