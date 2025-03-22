package Strategy;

public class GooglePayPayment implements PaymentStrategy {
    String UPI_ID;

    public GooglePayPayment(String upi_id)
    {
        this.UPI_ID = upi_id;
    }
    @Override
    public void Pay(int amount)
    {
        System.out.println("Payment Done Using Google Pay. UPI ID : " + UPI_ID + " Amount : " + amount);
    }
}
