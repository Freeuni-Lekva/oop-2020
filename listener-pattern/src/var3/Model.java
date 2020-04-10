package var3;

import java.util.HashSet;
import java.util.Set;

public class Model {
    private String value;
    private Set<ModelStateChangeListener> registeredListeners;

    public Model() {
        registeredListeners = new HashSet<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        for (ModelStateChangeListener listener : registeredListeners) {
            listener.modelChanged(this);
        }
    }

    public void registerView(ModelStateChangeListener listener) {
        registeredListeners.add(listener);
    }

    public void removeView(ModelStateChangeListener listener) {
        registeredListeners.remove(listener);
    }
}
