package com.chobocho.dbmanager;
import android.text.TextUtils;
import android.util.Log;

import java.util.*;

public class PasswdManager {
    public static final String TAG = "[ChoboPW] " + PasswdManager.class.getSimpleName();
    PasswdDBInf passwordDB = null;

    public PasswdManager(PasswdDBInf passwdDB) {
        passwordDB = passwdDB;
        Log.i(TAG, "Init");
    }

    public void insert() {
        // TODO implement here
    }

    public void delete() {
        // TODO implement here
    }

    public void update() {
        // TODO implement here
    }

    public String search(String keyword) {
        if (passwordDB == null) {
            Log.e(TAG, "password DB is null");
            return "";
        }
        return passwordDB.search(keyword);
    }

    public ArrayList<String> getItemList() {
        if (passwordDB == null) {
            return null;
        }
        return passwordDB.getItemList();
    }

    public ArrayList<String> getItemList(String query) {
        if (passwordDB == null) {
            return null;
        }

        if (TextUtils.isEmpty(query)) {
            return passwordDB.getItemList();
        }
        return passwordDB.getItemList(query);
    }
}