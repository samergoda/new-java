public sealed interface Payment permits CashPayment, CardPayment {
    void processPayment(OrderResponse order);
}