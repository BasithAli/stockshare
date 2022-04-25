package com.sharebrokering.util;

import com.sharebrokering.model.BestMatches;
import com.sharebrokering.model.CurrencyConv;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CurrencyConverterService {

    private static final String BASE_URL = "http://localhost:8080/aCurConvRS/webresources/curCodes";
    private static final String BASE_URL_REALTIME_CONV = "http://localhost:8080/aCurConvRS/webresources/exchangeRate/realtime-rate-in-gbp";

    public CurrencyConv getCurrencyConvertData(){

        return WebClient.create()
                .get()
                .uri(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CurrencyConv.class).block();
    }

    public CurrencyConv getRealTimeConversionData(String from){

        return WebClient.create()
                .get()
                .uri(BASE_URL_REALTIME_CONV+"?fromCur="+from)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CurrencyConv.class).block();
    }
}
