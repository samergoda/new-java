import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class Main {

    public static void main(String[] args) throws Exception {
            User user = fetchUser("1");
            System.out.println(user);
            OrderController orderController = new OrderController();
            Product p1 = new Product("1","test product",10);
            Order order1 = new Order("1",user, List.of(p1));
            OrderResponse orderResponse = orderController.create(order1);
            CardPayment card = new CardPayment();
            card.processPayment(orderResponse);


    }

    // Fetch real user from real api (using GitHub API since jsonplaceholder is blocked)
    public static User fetchUser(String userId) {
        try (HttpClient client = HttpClient.newBuilder().build()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/" + userId))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Parse JSON properly with Jackson
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.body());

            String name = jsonNode.get("name").asText();
            String email = jsonNode.get("email").asText();

            System.out.println("Fetched user: " + name + " (" + email + ")");

            return new User(userId, name, email);
        } catch (Exception e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }

        // Return default User
        return new User("1","Test","test@test.com");
    }


}