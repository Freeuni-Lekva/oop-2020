package var2;

import java.util.HashSet;
import java.util.Set;

public class Model {
    private String value;
    // If there is more than one class interested in value changes we have to keep them in different lists.
    private Set<ViewOne> registeredViewOnes;
    private Set<ViewTwo> registeredViewTwos;

    public Model() {
        registeredViewOnes = new HashSet<>();
        registeredViewTwos = new HashSet<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        for (ViewOne viewOne : registeredViewOnes) {
            viewOne.modelChanged(this);
        }
        // Another code duplication
        for (ViewTwo viewTwo : registeredViewTwos) {
            viewTwo.modelChanged(this);
        }
    }

    public void registerViewOne(ViewOne viewOne) {
        registeredViewOnes.add(viewOne);
    }

    public void removeViewOne(ViewOne viewOne) {
        registeredViewOnes.remove(viewOne);
    }

    // Yet another code duplication
    public void registerViewTwo(ViewTwo viewTwo) {
        registeredViewTwos.add(viewTwo);
    }

    public void removeViewTwo(ViewTwo viewTwo) {
        registeredViewTwos.remove(viewTwo);
    }
}
