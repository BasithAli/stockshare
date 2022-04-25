package com.sharebrokering.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalQuote {
    @JsonProperty("Global Quote")
    private QuoteEndpoint quoteEndpoint;

    public QuoteEndpoint getQuoteEndpoint() {
        return quoteEndpoint;
    }

    public void setQuoteEndpoint(QuoteEndpoint quoteEndpoint) {
        this.quoteEndpoint = quoteEndpoint;
    }
}
