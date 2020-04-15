public class Main {

    public static void main(String[] args) {
        CalcBrain brain = new CalcBrain();
        CalcView view = new GUICalcView();
        CalcController controller = new CalcController(brain, view);
        controller.start();
    }
}
