public class OrderService {

    public OrderResponse createOrder(Order order) {
        double total = order.products()
                .stream()
                .mapToDouble(Product::price)
                .sum();

        return new OrderResponse(order.id(), total);
    }
}