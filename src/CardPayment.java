public non-sealed class CardPayment implements Payment {
    @Override
    public void processPayment(Order order) {
        System.out.println("order payment successfully, order id: "+ order.id() + " by Card");
    }
}
