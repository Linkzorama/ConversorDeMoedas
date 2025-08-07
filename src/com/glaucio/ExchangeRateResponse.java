package com.glaucio;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ExchangeRateResponse {

    private String base_code;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    // Getter para base_code
    public String getBase_code() {
        return base_code;
    }

    // Getter para conversionRates
    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }
}
