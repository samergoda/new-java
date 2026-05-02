public record OrderResponse(String orderId, double totalPrice) {

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId='" + orderId + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

}