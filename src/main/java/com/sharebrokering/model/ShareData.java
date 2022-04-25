package com.sharebrokering.model;

import java.io.Serializable;

public class ShareData implements Serializable {
    private Share shareDetails;
    private int shareAmount;

    public Share getShareDetails() {
        return shareDetails;
    }

    public void setShareDetails(Share shareDetails) {
        this.shareDetails = shareDetails;
    }

    public int getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(int shareAmount) {
        this.shareAmount = shareAmount;
    }
}
