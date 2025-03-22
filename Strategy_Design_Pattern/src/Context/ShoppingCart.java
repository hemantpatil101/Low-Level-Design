package Context;

import Strategy.PaymentStrategy;

public class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy)
    {
        this.paymentStrategy = paymentStrategy;
    }

    public void makePayment(int amount)
    {
        if(paymentStrategy != null)
        {
            paymentStrategy.Pay(amount);
        }
        else
        {
            System.out.println("Please select valid Payment Method");
        }
    }
}
