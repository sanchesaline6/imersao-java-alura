import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class TopFilms {
    public static void main(String[] args) throws IOException, InterruptedException {

        // fazer uma conexão HTTP e buscar os TOP 250 filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> filmes = parser.parse(body);

        // manipular e exibir os dados
        for (Map<String, String> filme : filmes) {
            System.out.println("\u001b[m \u001b[1m \u001b[33mTítulo:   \u001b[37m" + filme.get("title") + "\u001b[m ");
            System.out.println("\u001b[m \u001b[33m Poster: \u001b[37m" + filme.get("image") + "\u001b[m ");
            System.out.println(
                    "\u001b[m \u001b[45m \u001b[33m Classificação: \u001b[37m" + filme.get("imDbRating") + "\u001b[m ");
            System.out.println("\u2B50");
            System.out.println();
        }
    }
}
