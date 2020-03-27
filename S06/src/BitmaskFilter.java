public class BitmaskFilter implements Filter {
    private static final int INITIAL_SIZE = 1;

    private int[] masks;
    private int numPresent;
    // Rep invariant:
    //   i % 32 -th bit of masks[i / 32] is one iff i-th element is marked as present
    //   numPresent equals to number of set bits in stored bit masks
    // Abstraction function:
    //   Represents set of present elements

    public BitmaskFilter() {
        numPresent = 0;
        masks = new int[INITIAL_SIZE];
    }

    @Override
    public int size() {
        return numPresent;
    }

    @Override
    public boolean isPresent(int index) {
        if (numBits() <= index) {
            return false;
        }
        int n = index / 32;
        int m = index % 32;
        return (masks[n] & (1 << m)) > 0;
    }

    @Override
    public void add(int index) {
        int n = index / 32;
        int m = index % 32;
        if (masks.length <= n) {
            grow(Math.max(n, masks.length * 2));
        }
        masks[n] |= 1 << m;
    }

    @Override
    public void remove(int index) {
        int n = index / 32;
        int m = index % 32;
        if (masks.length <= n) {
            return;
        }
        masks[n] &= ~(1 << m);
    }

    private int numBits() {
        return 32 * masks.length;
    }

    private void grow(int newSize) {
        int[] tmp = new int[newSize];
        for (int i = 0; i < masks.length; i++) {
            tmp[i] = masks[i];
        }
        masks = tmp;
    }
}
