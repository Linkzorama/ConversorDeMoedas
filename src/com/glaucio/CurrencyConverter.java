package com.glaucio;

import java.util.Map;

public class CurrencyConverter {

    public static double convert(double amount, String from, String to, Map<String, Double> rates) {
        if (!rates.containsKey(from) || !rates.containsKey(to)) {
            throw new IllegalArgumentException("Moeda não suportada.");
        }

        double rateFrom = rates.get(from);
        double rateTo = rates.get(to);

        // Converter de "from" para "USD" e depois para "to"
        double amountInUSD = amount / rateFrom;
        return amountInUSD * rateTo;
    }
}
