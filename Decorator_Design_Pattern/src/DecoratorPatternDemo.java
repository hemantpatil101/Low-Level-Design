import Components.Beverage;
import Components.Coffee;
import Components.Beverage;
import Components.Tea;
import Decorators.BeverageDecorator;
import Decorators.MilkDecorator;
import Decorators.SugarDecorator;
public class DecoratorPatternDemo {
    public static void main(String[] args) throws Exception {
        Beverage coffee = new Coffee();

        BeverageDecorator coffeeWithSugar = new SugarDecorator(coffee);
        System.out.println("Description : " + coffeeWithSugar.getDescription() + " || Cost : " + coffeeWithSugar.getCost());

        BeverageDecorator coffeeWithSugarAndMilk = new MilkDecorator(coffeeWithSugar);
        System.out.println("Description : " + coffeeWithSugarAndMilk.getDescription() + " || Cost : " + coffeeWithSugarAndMilk.getCost());
    }
}
