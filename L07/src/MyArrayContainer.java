public class MyArrayContainer implements MyContainer {
    private int[] arr;

    public MyArrayContainer(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public int get(int index) {
        return arr[index];
    }
}
