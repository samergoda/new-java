import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.security.NoSuchAlgorithmException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class Main {

    private static final HttpClient client = HttpClient.newHttpClient();

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

    // Fetch real user from real api
    public static User fetchUser(String id) throws Exception {
        try{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
                .timeout(Duration.ofSeconds(20))
                .GET()
                .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Parse JSON properly with Jackson
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.body());

            String userId = jsonNode.get("id").asText();
            String name = jsonNode.get("name").asText();
            String email = jsonNode.get("email").asText();

            System.out.println("Fetched user: " + name + " (" + email + ")");

            return new User(userId, name, email);
        } catch (Exception error) {
            System.out.println("Error fetching user: " + error.getMessage());
            throw error;
        }
    }


}