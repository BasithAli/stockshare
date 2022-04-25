package com.sharebrokering.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private double depositAmount;
    private String username;
    private String preferredExchangeCurrency;

    private List<ShareData> shareData = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ShareData> getShareData() {
        return shareData;
    }

    public void setShareData(List<ShareData> shareData) {
        this.shareData = shareData;
    }

    public String getPreferredExchangeCurrency() {
        return preferredExchangeCurrency;
    }

    public void setPreferredExchangeCurrency(String preferredExchangeCurrency) {
        this.preferredExchangeCurrency = preferredExchangeCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
