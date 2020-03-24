public class Candy extends Food {
    private int sweet;

    public Candy(String name, int sweet) {
        super(name);
        this.sweet = sweet;
    }

    public int getSweet() {
        return sweet;
    }

    @Override
    Candy foo() { return null; }

    public boolean same(Candy candy) {
        System.out.println("Candy: same");
        return super.same(candy) && (sweet == candy.getSweet());
    }
}
