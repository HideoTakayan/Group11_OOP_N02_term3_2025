package com.example.servingwebcontent;

import java.util.ArrayList;

import com.example.servingwebcontent.model.User;

public class AddUserToList {
    public ArrayList<User> addUserToList(User u) {
        ArrayList<User> al = new ArrayList<User>();
        al.add(u);
        return al;

    }

}
