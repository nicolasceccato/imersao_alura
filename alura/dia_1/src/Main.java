import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

    public static void main(String[] args) throws IOException, InterruptedException {
        //fazer conexao http para buscar os top 250 filmes
        URI endereco = URI.create(URL);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //pegar os dados q interessam (titulo, poster, classificacao)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for(Map<String,String> filme : listaDeFilmes){
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }


    }
}