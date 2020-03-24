public class Food {
    private String name;

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    Food foo() { return null; }

    public boolean same(Food food) {
        System.out.println("Food: same");
        return name.equals(food.getName());
    }
}
