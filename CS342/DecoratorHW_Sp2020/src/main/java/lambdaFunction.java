
public class lambdaFunction {
    interface ToDoubleBiInterface{ //my own version of ToDoubleBiInterface
        abstract Double appAsDouble(Double mean, Double value);
        //appAsDouble is the abstract method that is in ToDoubleBiInterface
    }
    public Double stanDHelperTwo(Double mean, Double value, ToDoubleBiInterface d)
    {
        return d.appAsDouble(mean, value);
    }

    public static void main(String[] args)
    {
        lambdaFunction l = new lambdaFunction();
        //concrete implementation of the functional interface
        System.out.println(l.stanDHelperTwo(10.0, 3.0, (a,b)-> Math.pow(a-b, 2)));
    }
}