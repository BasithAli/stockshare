package com.sharebrokering.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;



@JsonIgnoreProperties(ignoreUnknown = true)
public class BestMatches {

    @JsonProperty("bestMatches")
    private List<SearchEndpoint> searchEndpoints;

    public List<SearchEndpoint> getSearchEndpoints() {
        return searchEndpoints;
    }

    public void setSearchEndpoints(List<SearchEndpoint> searchEndpoints) {
        this.searchEndpoints = searchEndpoints;
    }
}
