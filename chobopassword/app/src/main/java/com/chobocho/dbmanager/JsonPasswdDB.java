package com.chobocho.dbmanager;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class JsonPasswdDB implements PasswdDBInf {
    static final String TAG = "[ChoboPW] " + JsonPasswdDB.class.getSimpleName();
    HashMap<String, PasswordItem> passwdTable;
    ArrayList<String> itemList = new ArrayList<>();

    private Context mContext = null;
    private String jsonFileName = "pw_data.json";

    public JsonPasswdDB(Context context) {
        Log.i(TAG, "Init");
        mContext = context;
        String jsonString = LoadJsonFileAsString();
        passwdTable = generatePasswdTable(jsonString);
    }

    private String LoadJsonFileAsString() {
        String jsonData = null;

        try {
            InputStream jsonInputStream = mContext.getAssets().open(jsonFileName);
            byte[] rawData = new byte[jsonInputStream.available()];
            jsonInputStream.read(rawData);
            jsonInputStream.close();
            jsonData = new String(rawData, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonData;
    }

    private HashMap<String, PasswordItem> generatePasswdTable(String passData) {
        HashMap<String, PasswordItem> passwdTable = new HashMap<>();

        try {
            JSONObject jsonObject = new JSONObject(passData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String title = object.getString("title");
                String id = object.getString("id");
                String passwd = object.getString("passwd");
                String memo = object.getString("memo");

                String hashKey = String.format("%03d: %s",i,title);

                PasswordItem passwordItem = new PasswordItem(title, id, passwd, memo);
                passwdTable.put(hashKey, passwordItem);

                itemList.add(hashKey);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return passwdTable;
    }

    /**
     * 
     */
    public void insert() {
        // TODO implement here
    }

    /**
     * 
     */
    public void delete() {
        // TODO implement here
    }

    /**
     * 
     */
    public void update() {
        // TODO implement here
    }

    /**
     *
     * @return
     */
    public String search(String keyword) {
        Log.i(TAG, "Search is short!" + keyword);
        if (passwdTable == null || keyword.length() < 5) {
            Log.i(TAG, "Search is short " + keyword);
            return "";
        }

        PasswordItem item = passwdTable.get(keyword);

        if (item == null) {
            return "";
        }

        return item.toString();
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