package com.example.servingwebcontent;

import com.example.servingwebcontent.database.insertToAiven;
import com.example.servingwebcontent.model.User;

public class WriteToSQLDb {
    public void writeToDb(User u) {
        insertToAiven ac = new insertToAiven();
        ac.insertToAivenDb(u);
    }

}
