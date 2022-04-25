package com.sharebrokering.service;

import com.sharebrokering.model.ConversionData;
import com.sharebrokering.model.Share;
import com.sharebrokering.model.ShareData;
import com.sharebrokering.model.User;

import java.util.List;

public interface ShareService {
    List<Share> getShareDetails(String companySymbol);
    boolean purchaseShare(String companySymbol,String user, int amount);
    boolean sellShare(String companySymbol,String user, int amount);
    List<ShareData> listUserShares(String user);
    boolean addUser(User user);
    boolean depositAmount(String username, double depositAmount);
    boolean withdrawAmount(String username, double amount);
    List<User> viewUsers();
    List<ConversionData> getConversionData();
    List<ConversionData> getRealTimeConversionData(String from);
}
