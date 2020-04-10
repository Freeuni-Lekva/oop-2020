package var2;

public class ViewOne {
    private final String name;
    private String value;

    public ViewOne(String name) {
        this.name = name;
    }

    public void modelChanged(Model model) {
        value = model.getValue();
        System.out.println("ViewOne " + name + " received new value + " + value);
    }

    public String getValue() {
        return value;
    }
}
