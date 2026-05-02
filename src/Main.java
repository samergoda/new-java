import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {
        User user = fetchUser("1");
        System.out.println(user);
        OrderController orderController = new OrderController();
        Product p1 = new Product("1","test product",10);
        Order order1 = new Order("1",user, List.of(p1));
        orderController.create(order1);
        CardPayment card = new CardPayment();
        card.processPayment(order1);
    }

    // Fetch real user from real api
    public static User fetchUser(String id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

//        ObjectMapper mapper = new ObjectMapper();

        System.out.println("response: " + response.body());

        // TODO: parse JSON properly
//        return new User("1","samer","test@test.com");
        return new User(id, response.body().split("\"name\":")[1].split(",")[0], response.body().split("\"email\":")[1].split(",")[0]);
    }


}