package var1;

public class View {
    private final String name;
    private String value;

    public View(String name) {
        this.name = name;
    }

    public void modelChanged(Model model) {
        value = model.getValue();
        System.out.println("View " + name + " received new value + " + value);
    }

    public String getValue() {
        return value;
    }
}
