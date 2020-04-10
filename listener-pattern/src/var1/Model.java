package var1;

import java.util.HashSet;
import java.util.Set;

public class Model {
    private String value;
    private Set<View> registeredViews;

    public Model() {
        registeredViews = new HashSet<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        for (View view : registeredViews) {
            view.modelChanged(this);
        }
    }

    public void registerView(View view) {
        registeredViews.add(view);
    }

    public void removeView(View view) {
        registeredViews.remove(view);
    }
}
