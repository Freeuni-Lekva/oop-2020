public interface Printer {
    default public Super print(Super s) {
        System.out.println(s.getValue());
        return s;
    }
}
