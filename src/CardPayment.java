public non-sealed class CardPayment implements Payment {
    @Override
    public void processPayment(OrderResponse order) {
        System.out.println("order payment successfully, order id: "+ order.orderId() + " by Card");
    }
}
