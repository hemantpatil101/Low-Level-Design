package Strategy;

public class CreditCardPayment implements PaymentStrategy{
    String Card_No;

    public CreditCardPayment(String card_no)
    {
        this.Card_No = card_no;
    }
    @Override
    public void Pay(int amount)
    {
        System.out.println("Payment Done Using Credit Card. Card No : " + Card_No + " Amount : " + amount);
    }
}
