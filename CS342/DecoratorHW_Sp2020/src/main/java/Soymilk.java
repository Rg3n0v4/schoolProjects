public class Soymilk extends CoffeeDecorator {
    private double cost = .85;
    Soymilk(Coffee specialCoffee){
        super(specialCoffee);
    }

    public double makeCoffee() {
        return specialCoffee.makeCoffee() + addSoymilk();
    }

    private double addSoymilk() {

        System.out.println(" + soy milk: $.85");

        return cost;
    }
}
