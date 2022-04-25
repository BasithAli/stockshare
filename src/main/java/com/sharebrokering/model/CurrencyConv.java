package com.sharebrokering.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyConv {
    @JsonProperty("CurrConv")
    private List<ConversionData> conversionDataList;

    public List<ConversionData> getConversionDataList() {
        return conversionDataList;
    }

    public void setConversionDataList(List<ConversionData> conversionDataList) {
        this.conversionDataList = conversionDataList;
    }
}
