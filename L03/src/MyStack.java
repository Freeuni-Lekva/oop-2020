import java.util.Collection;

public class MyStack<T> {
    private int logLen;
    private Object[] elems;

    public MyStack() {
        logLen = 0;
        elems = new Object[4];
    }

    public void add(T elem) {
        if (elems.length == logLen) {
            Object[] tmp = new Object[2 * logLen];
            for (int i = 0; i < elems.length; i++) {
                tmp[i] = elems[i];
            }
            elems = tmp;
        }
        elems[logLen] = elem;
        logLen++;
    }

    public T pop() {
        if (logLen > 0) {
            logLen--;
            T tmp = (T) elems[logLen];
            elems[logLen] = null;
            return tmp;
        } else {
            // raise
            return null;
        }
    }

    void addAll(Collection<T> c) {
        for (T elem : c) {
            add(elem);
        }
    }

    int size() {
        return logLen;
    }
}
