package com.glaucio;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "b814d0bb7195e0c5a5163dd11"; // 🔑 Substitua pela sua chave real da API
        String baseCurrency = "USD";

        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseStrBuilder.append(line);
            }
            reader.close();

            String jsonResponse = responseStrBuilder.toString();
            Gson gson = new Gson();
            ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);

            Map<String, Double> rates = response.getConversionRates();

            boolean running = true;
            while (running) {
                System.out.println("\n====== Conversor de Moedas ======");
                System.out.println("1. USD → BRL");
                System.out.println("2. USD → EUR");
                System.out.println("3. USD → JPY");
                System.out.println("4. USD → GBP");
                System.out.println("5. USD → CAD");
                System.out.println("6. USD → ARS");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                int option = scanner.nextInt();

                String targetCurrency = null;

                switch (option) {
                    case 1: targetCurrency = "BRL"; break;
                    case 2: targetCurrency = "EUR"; break;
                    case 3: targetCurrency = "JPY"; break;
                    case 4: targetCurrency = "GBP"; break;
                    case 5: targetCurrency = "CAD"; break;
                    case 6: targetCurrency = "ARS"; break;
                    case 0:
                        running = false;
                        System.out.println("Encerrando...");
                        continue;
                    default:
                        System.out.println("Opção inválida!");
                        continue;
                }

                System.out.print("Digite o valor em USD: ");
                double amount = scanner.nextDouble();

                double converted = CurrencyConverter.convert(amount, baseCurrency, targetCurrency, rates);
                System.out.printf("%.2f USD = %.2f %s\n", amount, converted, targetCurrency);
            }

        } catch (Exception e) {
            System.out.println("Erro ao acessar a API ou processar os dados.");
            e.printStackTrace();
        }
    }
}
