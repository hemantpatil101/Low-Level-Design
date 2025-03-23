package Decorators;

import Components.Beverage;

public class MilkDecorator extends BeverageDecorator{
    public MilkDecorator(Beverage milkDecorator)
    {
        super(milkDecorator);
    }

    public String getDescription()
    {
        return super.getDescription() + " , Milk";
    }

    public int getCost()
    {
        return super.getCost() + 10;
    }
}
