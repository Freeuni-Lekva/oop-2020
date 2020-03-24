public abstract class Electronic implements Device {
    public abstract void plugin();

    public void on() {
        System.out.println("Turned on");
    }

    public void off() {
        System.out.println("Turned off");
    }
}

public class Charger {
    public void charge(Mobile e) {
        e.plugin();
    }
}
