package dev.vasishta.invest.track.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.vasishta.invest.track.constant.GenericConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public final class GeneralUtils {
    public static double getCurrentPriceFromMC(RestTemplate template, String exchange, String mcSymbol) {
        String url = GenericConstants.MONEY_CONTROL_API + exchange.toLowerCase() + "/equitycash/" + mcSymbol;
        ResponseEntity<String> response = template.getForEntity(url, String.class);
        JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
        double currentPrice = Double.parseDouble(jsonObject.getAsJsonObject("data").getAsJsonPrimitive("pricecurrent").getAsString());
        return currentPrice;
    }
}
