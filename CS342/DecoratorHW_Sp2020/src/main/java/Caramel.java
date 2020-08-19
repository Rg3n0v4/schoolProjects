public class Caramel extends CoffeeDecorator {
    private double cost = .75;
    Caramel(Coffee specialCoffee){
        super(specialCoffee);
    }

    public double makeCoffee() {
        return specialCoffee.makeCoffee() + addCaramel();
    }

    private double addCaramel() {

        System.out.println(" + pump of caramel: $.75");

        return cost;
    }
}
