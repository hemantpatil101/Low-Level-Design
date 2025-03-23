package Decorators;

import Components.Beverage;

public class SugarDecorator extends BeverageDecorator{
    public SugarDecorator(Beverage sugarDecorator)
    {
        super(sugarDecorator);
    }

    public String getDescription()
    {
        return super.getDescription() + " , Sugar";
    }

    public int getCost()
    {
        return super.getCost() + 5;
    }
}
