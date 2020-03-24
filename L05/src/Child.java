public class Child {
    public void eat(Food food) {
        if (food.same(new Food("brocolli"))) {
            System.out.println("foo");
        }
        System.out.printf("Eating: %s\n", food.getName());
    }











    public void eat(Candy candy) {
        if (candy.same(new Candy("sour patch", 10))) {
            System.out.println("foo");
        }
        System.out.printf("Eating: %s\n", candy.getName());
    }
}
