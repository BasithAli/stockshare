package com.sharebrokering.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharebrokering.model.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Util {
    static List<User> users;

    public static void saveUserList(List<User> list) {
        FileOutputStream file = null;
        try {
            Gson json = new Gson();
            String userDataList = json.toJson(list);
            file = new FileOutputStream("users.txt") ;
            file.write(userDataList.getBytes());

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (file != null){
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static List<User> getUsers() {
        if(users != null){
            return users;
        }
        try {
            String fileData = new String(Files.readAllBytes(Paths.get("users.txt")));
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            List<User> list = new Gson().fromJson(fileData, listType);
            users = list;
            return users;

        } catch (Exception f) {
            System.err.println(f);
        }
        return null;

    }

}
