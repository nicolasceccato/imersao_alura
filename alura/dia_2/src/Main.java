import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {

    //  private static final String URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

    public static void main(String[] args) throws Exception {
        //fazer conexao http para buscar os top 250 filmes
        //pegar os dados q interessam (titulo, poster, classificacao)

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-02-20&end_date=2023-02-22";

        CLienteHttp http = new CLienteHttp();
        String json = http.buscaDados(url);


        //exibir e manipular os dados
        ExtratorDeConteudo extratorDeConteudoNasa = new ExtratorDeConteudoNasa();
        List<Conteudo> conteudos = extratorDeConteudoNasa.extraiConteudos(json);
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }


    }
}