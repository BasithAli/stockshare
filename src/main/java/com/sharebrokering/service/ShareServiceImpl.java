package com.sharebrokering.service;

import com.sharebrokering.model.ConversionData;
import com.sharebrokering.model.QuoteEndpoint;
import com.sharebrokering.model.Share;
import com.sharebrokering.model.ShareData;
import com.sharebrokering.model.User;
import com.sharebrokering.util.CurrencyConverterService;
import com.sharebrokering.util.ShareDetailsService;
import com.sharebrokering.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareDetailsService shareDetailsService;
    @Autowired
    private CurrencyConverterService currencyConverterService;

    @Override
    public List<Share> getShareDetails(String companySymbol) {

        shareDetailsService.searchData(companySymbol);
        List<Share> shareList = new ArrayList<>();
        shareDetailsService.searchData(companySymbol).getSearchEndpoints().stream().forEach(
                searchEndpoint -> {
                    QuoteEndpoint qe = shareDetailsService.getQuote(companySymbol).getQuoteEndpoint();
                    Share share = new Share();
                    share.setCompanyName(searchEndpoint.getName());
                    share.setCompanySymbol(searchEndpoint.getSymbol());
                    share.setCurrency(searchEndpoint.getCurrency());
                    share.setPrice(qe.getPrice());
                    share.setNumber(qe.getVolume());
                    share.setDate(qe.getLatestTradingDay());
                    shareList.add(share);
                }
        );

        return shareList;

    }


    @Override
    public boolean purchaseShare(String companySymbol, String user, int amount) {
        List<User> userList = Util.getUsers();
        Optional<User> userObj = userList.stream().filter(user1 -> user1.getUsername().equals(user)).findFirst();
        if (userObj.isPresent()) {
            QuoteEndpoint qe = shareDetailsService.getQuote(companySymbol).getQuoteEndpoint();
            Optional<ShareData> shareDetails = userObj.get().getShareData().stream().filter(shareData -> shareData.getShareDetails().getCompanySymbol().equals(companySymbol)).findFirst();
            if (Float.parseFloat(qe.getPrice()) * amount <= userObj.get().getDepositAmount() && amount <= Integer.parseInt(qe.getVolume())) {
                if (shareDetails.isPresent()) {
                    shareDetails.get().setShareAmount(shareDetails.get().getShareAmount() + amount);
                    userObj.get().setDepositAmount(userObj.get().getDepositAmount() - Float.parseFloat(qe.getPrice()) * amount);
                    updateUserDetails();
                    return true;
                } else {
                    List<Share> shareList = getShareDetails(companySymbol);
                    if (shareList.size() > 0) {
                        ShareData shareData = new ShareData();
                        shareData.setShareDetails(shareList.get(0));
                        shareData.setShareAmount(amount);
                        userObj.get().getShareData().add(shareData);
                        updateUserDetails();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean sellShare(String companySymbol, String user, int amount) {
        List<User> userList = Util.getUsers();
        Optional<User> userObj = userList.stream().filter(user1 -> user1.getUsername().equals(user)).findFirst();
        if (userObj.isPresent()) {
            Optional<ShareData> shareDetails = userObj.get().getShareData().stream().filter(shareData -> shareData.getShareDetails().getCompanySymbol().equals(companySymbol)).findFirst();
            if (shareDetails.isPresent() && shareDetails.get().getShareAmount() >= amount) {
                QuoteEndpoint qe = shareDetailsService.getQuote(companySymbol).getQuoteEndpoint();
                shareDetails.get().setShareAmount(shareDetails.get().getShareAmount() - amount);
                userObj.get().setDepositAmount(userObj.get().getDepositAmount() + Float.parseFloat(qe.getPrice()) * amount);
                updateUserDetails();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ShareData> listUserShares(String user) {
        List<User> userList = Util.getUsers();
        if(userList != null){
            Optional<User> userObj = userList.stream().filter(user1 -> user1.getUsername().equals(user)).findFirst();
            if (userObj.isPresent()) {
              Optional<ConversionData> conversionDataObj =  currencyConverterService.getCurrencyConvertData().getConversionDataList().stream()
                        .filter(conversionData -> conversionData.getCode().equals(userObj.get().getPreferredExchangeCurrency())).findFirst();

              if(conversionDataObj.isPresent()){
                  for (ShareData shareData: userObj.get().getShareData()) {
                      double price = Double.parseDouble(shareData.getShareDetails().getPrice()) * conversionDataObj.get().getRate();
                      shareData.getShareDetails().setPriceAfterPreferredCurrencyConv(Double.toString(price));
                  }
              }

                return userObj.get().getShareData();
            }
        }

        return null;
    }

    @Override
    public boolean addUser(User user) {
        List<User> userList = Util.getUsers();
        if (userList != null) {
            boolean value = Util.getUsers().stream().anyMatch(user1 -> user1.getUsername().equals(user.getUsername()));
            if (value) {
                return false;
            }
        }
        if (userList == null) {
            userList = new ArrayList<>();
        }
        userList.add(user);
        Util.saveUserList(userList);
        return true;
    }

    @Override
    public boolean depositAmount(String username, double depositAmount) {
        List<User> userList = Util.getUsers();
        Optional<User> user = userList.stream().filter(user1 -> user1.getUsername().equals(username)).findFirst();
        if (user.isPresent()) {
            user.get().setDepositAmount(user.get().getDepositAmount() + depositAmount);
            updateUserDetails();
            return true;
        }
        return false;
    }

    @Override
    public boolean withdrawAmount(String username, double amount) {
        List<User> userList = Util.getUsers();
        Optional<User> user = userList.stream().filter(user1 -> user1.getUsername().equals(username)).findFirst();
        if (user.isPresent() && amount <= user.get().getDepositAmount()) {

            user.get().setDepositAmount(user.get().getDepositAmount() - amount);
            updateUserDetails();
            return true;

        }
        return false;
    }

    @Override
    public List<User> viewUsers() {
        return Util.getUsers();
    }

    @Override
    public List<ConversionData> getConversionData() {
        return currencyConverterService.getCurrencyConvertData().getConversionDataList();
    }

    @Override
    public List<ConversionData> getRealTimeConversionData(String from) {
        return currencyConverterService.getRealTimeConversionData(from).getConversionDataList();
    }

    private void updateUserDetails() {
        Util.saveUserList(Util.getUsers());
    }

}
