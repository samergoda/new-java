public sealed interface Payment permits CashPayment, CardPayment {
    void processPayment(Order order);
}