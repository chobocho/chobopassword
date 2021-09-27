package com.chobocho.dbmanager;

import java.util.ArrayList;

public interface PasswdDBInf {
    public void insert();
    public void delete();
    public void update();
    public String search(String keyword);
    public ArrayList<String> getItemList();
    public ArrayList<String> getItemList(String query);
}