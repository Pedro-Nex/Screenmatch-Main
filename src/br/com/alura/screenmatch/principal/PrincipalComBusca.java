package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PrincipalComBusca {
    static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite um filme para busca: ");
        var busca = sc.nextLine();
        String apiKey = System.getenv("OMDB_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Defina a variável de ambiente OMDB_API_KEY.");
        }
        String endereco = "https://www.omdbapi.com/?t="
                + URLEncoder.encode(busca, StandardCharsets.UTF_8)
                + "&apikey=" + URLEncoder.encode(apiKey, StandardCharsets.UTF_8);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            System.out.println(json);

            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println(meuTituloOmdb);
            // try {
            Titulo meuTitulo = new Titulo(meuTituloOmdb);
            System.out.println("Titulo já convertido.");
            System.out.println(meuTitulo);
        } catch (NumberFormatException e) {
            System.out.println("Aconteceu um erro.");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Aconteceu um erro.");
            System.out.println(e.getMessage());
        } catch (ErroDeConversaoDeAnoException e) {
            System.out.println("Aconteceu um erro.");
            System.out.println(e.getMessage());
        }
        System.out.println("O programa finalizou corretamente!");
    }
}
