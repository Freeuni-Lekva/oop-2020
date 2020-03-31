public class MyHeapContainer implements MyContainer {
    private static final int INIT_LEN = 4;

    private int[] arr;
    private int logLen;

    public MyHeapContainer() {
        arr = new int[INIT_LEN];
        logLen = 0;
    }

//    @Override
//    public int getMax() {
//        if (size() == 0) {
//            return 0;
//        }
//        System.out.print("Called getMax from MyHeapContainer");
//        return arr[0];
//    }

    @Override
    public int size() {
        return logLen;
    }

    @Override
    public int get(int index) {
        if (index >= logLen) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return arr[index];
    }

    public void add(int x) {
        if (logLen == arr.length) {
            grow();
        }
        arr[logLen] = x;
        for (int i = logLen; i > 0 && arr[i] > arr[i / 2]; i /= 2) {
            int tmp = arr[i];
            arr[i] = arr[i / 2];
            arr[i / 2] = tmp;
        }
        logLen++;
    }

    private void grow() {
        int[] tmp = new int[arr.length * 2];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        arr = tmp;
    }
}
