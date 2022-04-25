package com.sharebrokering.util;

import com.sharebrokering.model.BestMatches;
import com.sharebrokering.model.GlobalQuote;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class ShareDetailsService {
    private static final String API_KEY = "c1e5ef8341mshb830e5721e41090p1300dbjsnb8d19d36a113";
    private static final String BASEURL = "https://alpha-vantage.p.rapidapi.com/query?function=";

    public BestMatches searchData(String symbol){


        return WebClient.create()
                .get()
                .uri(BASEURL+"SYMBOL_SEARCH&keywords="+symbol+"&datatype=json")
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .header("X-RapidAPI-Key", API_KEY)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BestMatches.class).block();

    }

    public GlobalQuote getQuote(String symbol){

        return WebClient.create()
                .get()
                .uri(BASEURL+"GLOBAL_QUOTE&symbol="+symbol+"&datatype=json")
                .header("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .header("X-RapidAPI-Key", API_KEY)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GlobalQuote.class).block();
    }
}
