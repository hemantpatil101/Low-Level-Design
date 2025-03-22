import Context.ShoppingCart;
import Strategy.CreditCardPayment;
import Strategy.GooglePayPayment;
import Strategy.PaymentStrategy;

public class StrategyPatternDemo {
    public static void main(String[] args) throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setPaymentStrategy(new CreditCardPayment("1234-5678-9123"));
        shoppingCart.makePayment(100);

        shoppingCart.setPaymentStrategy(new GooglePayPayment("9876543210"));
        shoppingCart.makePayment(200);
    }
}
