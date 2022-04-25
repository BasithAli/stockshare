package com.sharebrokering.model;

import java.io.Serializable;

public class Share implements Serializable {
    private String companyName;
    private String companySymbol;
    private String currency;
    private String number;
    private String price;
    private String date;
    private String priceAfterPreferredCurrencyConv;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanySymbol() {
        return companySymbol;
    }

    public void setCompanySymbol(String companySymbol) {
        this.companySymbol = companySymbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriceAfterPreferredCurrencyConv() {
        return priceAfterPreferredCurrencyConv;
    }

    public void setPriceAfterPreferredCurrencyConv(String priceAfterPreferredCurrencyConv) {
        this.priceAfterPreferredCurrencyConv = priceAfterPreferredCurrencyConv;
    }
}
