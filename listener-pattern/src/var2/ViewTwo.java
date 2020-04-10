package var2;

public class ViewTwo {
    private final String name;
    private String value;

    public ViewTwo(String name) {
        this.name = name;
    }

    public void modelChanged(Model model) {
        value = model.getValue();
        System.out.println("ViewTwo " + name + " received new value + " + value);
    }

    public String getValue() {
        return value;
    }
}
