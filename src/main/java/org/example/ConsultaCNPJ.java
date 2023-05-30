package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsultaCNPJ {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("CNPJ: ");
        String cnpj = s.nextLine();

        try {
            // URL da API do ReceitaWS com o CNPJ a ser consultado
            String url = "https://www.receitaws.com.br/v1/cnpj/" + cnpj;

            // Criação da conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Verifica se a requisição foi bem sucedida (código 200)
            if (connection.getResponseCode() == 200) {
                // Leitura da resposta da API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                //System.out.println(response.toString());

                // Conversão do JSON para objeto Java usando Gson
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                ResultadoCNPJ resultado = gson.fromJson(response.toString(), ResultadoCNPJ.class);

                // Exemplo de acesso aos dados do resultado
                System.out.println("Nome: " + resultado.getNome());
                System.out.println("CNPJ: " + resultado.getCnpj());
                System.out.println("Situação: " + resultado.getSituacao());
                System.out.println("Abertura: " + resultado.getAbertura());
                System.out.println("Bairro: " + resultado.getBairro());
                System.out.println("CEP: " + resultado.getCep());
                //System.out.println("Billing: " + resultado.getBilling());
                /*List<String> atividadesPrincipais = Collections.singletonList(resultado.getAtividade_principal().toString());
                System.out.println("Atividades Principais:");
                for (String atividade : atividadesPrincipais) {
                    System.out.println("- " + atividade);
                }*/
                System.out.println("Capital Social: " + resultado.getCapital_social());
                System.out.println("Complemento: " + resultado.getComplemento());
                System.out.println("Data Situação: " + resultado.getData_situacao());
                System.out.println("Data Situação Especial: " + resultado.getData_situacao_especial());
                System.out.println("EFR: " + resultado.getEfr());
                System.out.println("Email: " + resultado.getEmail());
                //System.out.println("Extra: " + resultado.getExtra());
                System.out.println("Fantasia: " + resultado.getFantasia());
                System.out.println("Logradouro: " + resultado.getLogradouro());
                System.out.println("Motivo Situação: " + resultado.getMotivo_situacao());
                System.out.println("Tipo: " + resultado.getTipo());
                System.out.println("Porte: " + resultado.getPorte());
                System.out.println("Natureza Jurídica: " + resultado.getNatureza_juridica());
                System.out.println("Número: " + resultado.getNumero());
                System.out.println("Município: " + resultado.getMunicipio());
                System.out.println("UF: " + resultado.getUf());
                System.out.println("Telefone: " + resultado.getTelefone());
                System.out.println("Última Atualização: " + resultado.getUltima_atualizacao());
                System.out.println("Status: " + resultado.getStatus());
                System.out.println("Situação Especial: " + resultado.getSituacao_especial());
                System.out.println("Data Situação Especial: " + resultado.getData_situacao_especial());
                //System.out.println("QSA: " + resultado.getQsa());



                // Outras operações com os dados...

            } else {
                System.out.println("Erro na consulta. Código: " + connection.getResponseCode());
            }

            // Fechamento da conexão
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

