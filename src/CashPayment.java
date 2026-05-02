public non-sealed class CashPayment implements Payment{
    @Override
    public void processPayment(OrderResponse order) {
        System.out.println("order payment successfully, order id: "+ order.orderId() + " by Card");
    }
}
