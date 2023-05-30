package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Consulta_InscriçãoEstadual {
    public static void main(String[] args) {
        String url = "https://apigateway.conectagov.estaleiro.serpro.gov.br/api-cpf-light-trial/v2/consulta/cpf";
        String cpf = "12345678900";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);

        try {
            // Define o conteúdo da requisição como um objeto JSON contendo o CPF
            String json = "{\"cpf\": \"" + cpf + "\"}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-type", "application/json");

            // Executa a requisição
            HttpResponse response = httpClient.execute(httpPost);

            // Obtém a resposta
            HttpEntity responseEntity = response.getEntity();
            String responseBody = EntityUtils.toString(responseEntity);

            // Faça o que for necessário com a resposta
            System.out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
