public non-sealed class CashPayment implements Payment{
    @Override
    public void processPayment(Order order) {
        System.out.println("order payment successfully, order id: "+ order.id() + " by Card");
    }
}
