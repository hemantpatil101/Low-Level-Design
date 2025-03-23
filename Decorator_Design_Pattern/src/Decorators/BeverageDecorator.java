package Decorators;

import Components.Beverage;
public class BeverageDecorator implements Beverage{
    protected Beverage beverage;

    public BeverageDecorator(Beverage beverage)
    {
        this.beverage = beverage;
    }

    public String getDescription()
    {
        return beverage.getDescription();
    }

    public int getCost()
    {
        return beverage.getCost();
    }
}
