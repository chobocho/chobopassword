package com.chobocho.dbmanager;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class sqlLite3PasswdDB implements PasswdDBInf {
    static final String TAG = sqlLite3PasswdDB.class.getSimpleName();
    ArrayList<String> itemList = new ArrayList<>();
    Context mContext;

    public sqlLite3PasswdDB(Context context) {
        Log.i(TAG, "Init");
        mContext = context;
    }

    @Override
    public void insert() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public String search(String keyword) {
        return "";
    }

    @Override
    public ArrayList<String> getItemList() {
        return itemList;
    }

    @Override
    public ArrayList<String> getItemList(String query) {
        ArrayList<String> filteredList = new ArrayList<>();
        for (String item : itemList) {
            String title = item.substring(5);
            if (title.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }

        return filteredList;
    }
}