import java.util.List;

public record Order(String id, User user, List<Product> products) {}