public interface MyContainer {
    public int size();

    public int get(int index);

    default public int getMax() {
        System.out.print("Called getMax from MyContainer");
        if (size() == 0) {
            return 0;
        }
        int ret = get(0);
        for (int i = 1; i < size(); ++i) {
            ret = Math.max(ret, get(i));
        }
        return ret;
    }
}