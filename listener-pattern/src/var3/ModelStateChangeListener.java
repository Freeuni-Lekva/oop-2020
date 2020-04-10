package var3;

// Introducing this interface avoids code duplication in Model class.
public interface ModelStateChangeListener {

    public void modelChanged(Model model);
}
