package part2;


public class Test {
    public static void invoke(Parent parent) {
        parent.go();
    }

    public static void invoke(Child child) {
        child.go();
    }

    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();
        Parent parentRefChildObj = new Child();
        invoke(parent);
        invoke(child);
        invoke(parentRefChildObj);
    }

}