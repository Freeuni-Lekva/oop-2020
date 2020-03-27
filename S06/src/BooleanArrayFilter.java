import java.util.ArrayList;
import java.util.List;

public class BooleanArrayFilter implements Filter {
    private static final int INITIAL_SIZE = 4;

    private boolean[] presence;
    private int numPresent;
    // Rep invariant:
    //   presence[i] == true iff i-th element is marked as present
    //   numPresent equals to number of true-s in presence array
    // Abstraction function:
    //   Represents set of present elements

    public BooleanArrayFilter() {
        presence = new boolean[INITIAL_SIZE];
        numPresent = 0;
    }

    @Override
    public int size() {
        return numPresent;
    }

    @Override
    public boolean isPresent(int index) {
        if (index < 0 || index >= presence.length) {
            return false;
        }
        return presence[index];
    }

    @Override
    public void add(int index) {
        if (index >= presence.length) {
            grow(Math.max(index + 1, presence.length * 2));
        }
        if (!presence[index]) {
            numPresent++;
        }
        presence[index] = true;
        checkRep();
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= presence.length) {
            return;
        }
        if (presence[index]) {
            numPresent--;
        }
        presence[index] = false;
        checkRep();
    }

    private void grow(int newSize) {
        boolean[] tmp = new boolean[newSize];
        for (int i = 0; i < presence.length; i++) {
            tmp[i] = presence[i];
        }
        presence = tmp;
    }

    private void checkRep() {
        int countTrues = 0;
        for (int i = 0; i < presence.length; i++) {
            if (presence[i]) {
                countTrues++;
            }
        }
        assert countTrues == numPresent;
    }
}
