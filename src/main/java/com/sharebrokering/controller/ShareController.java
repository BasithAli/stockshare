package com.sharebrokering.controller;

import com.sharebrokering.model.ConversionData;
import com.sharebrokering.model.Share;
import com.sharebrokering.model.ShareData;
import com.sharebrokering.model.User;
import com.sharebrokering.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShareController {

    @Autowired
    private ShareService shareService;

    @GetMapping(value = "/view/{symbol}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Share>> listShares(@PathVariable("symbol") String symbol) {
        List<Share> shareData = shareService.getShareDetails(symbol);
        return ResponseEntity.status(200).body(shareData);
    }
    @GetMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = shareService.viewUsers();
        return ResponseEntity.status(200).body(users);
    }
    @GetMapping(value = "/user-share/{user}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShareData>> listUserShare(@PathVariable("user") String user) {
        List<ShareData> shareData = shareService.listUserShares(user);
        return ResponseEntity.status(200).body(shareData);
    }

    @PostMapping(value = "/add-user",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (shareService.addUser(user)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/deposit-amount/{user}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> depositAmount(@PathVariable("user") String user, @RequestBody String amount) {
        if (shareService.depositAmount(user,Double.parseDouble(amount))){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/withdraw-amount/{user}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> withdrawAmount(@PathVariable("user") String user, @RequestBody String amount) {
        if (shareService.withdrawAmount(user,Double.parseDouble(amount))){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/sell/{user}/{symbol}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sellShare(@PathVariable("user") String user,@PathVariable("symbol") String symbol, @RequestBody int amount) {
        if (shareService.sellShare(symbol,user,amount)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/purchase/{user}/{symbol}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> purchaseShare(@PathVariable("user") String user,@PathVariable("symbol") String symbol, @RequestBody int amount) {
        if (shareService.purchaseShare(symbol,user,amount)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping(value = "/conversion-data",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConversionData>> listConversionData() {
        List<ConversionData> shareData = shareService.getConversionData();
        return ResponseEntity.status(200).body(shareData);
    }

    @GetMapping(value = "/realtime-conversion-data",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConversionData>> listRealTimeConversionData(@RequestParam("fromCur") String from) {
        List<ConversionData> shareData = shareService.getRealTimeConversionData(from);
        return ResponseEntity.status(200).body(shareData);
    }
}
