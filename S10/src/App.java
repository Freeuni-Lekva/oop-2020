import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class App {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
        } catch (Exception e) {
            throw new AssertionError(e);
        }

        Calculator calc = new Calculator();
        calc.setVisible(true);
    }
}
