import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws Exception {

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);

        var generator = new StickerGenerator();

        for (Map<String, String> movie : moviesList
        ) {

            String imageUrl = movie.get("image");
            String movieTitle = movie.get("title");
            String filename = movieTitle + ".png";

            InputStream inputStream = new URL(imageUrl).openStream();

            generator.create(inputStream, filename);

            System.out.println("\u001B[1m Título: \u001B[31;1m" + movieTitle + "\u001B[0m");
            System.out.println("\u001B[1m Poster: " + movie.get("image"));
            System.out.println("\u001B[1m Classificação: \u001B[31;1m " + movie.get("imDbRating") + "\u001B[0m");
            System.out.println("\u001B[1m" + ratingToStars(movie.get("imDbRating")));
            System.out.println();
        }
    }
        private static String ratingToStars(String rating) {
            double ratingValue = Double.parseDouble(rating);
            int fullStars = (int) Math.floor(ratingValue);
            boolean halfStar = (ratingValue - fullStars) >= 0.5;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fullStars; i++) {
                sb.append("⭐️");
            }
            if (halfStar) {
                sb.append("🌟");
            }
            return sb.toString();
        }

    }

