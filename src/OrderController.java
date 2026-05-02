public class OrderController {

    private final OrderService service = new OrderService();

    public OrderResponse create(Order order) {
        return service.createOrder(order);
    }
}