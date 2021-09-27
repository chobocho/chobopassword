package com.chobocho.dbmanager;

import androidx.annotation.NonNull;

class PasswordItem {
    public String title = "";
    public String id = "";
    public String passwd = "";
    public String memo = "";

    public PasswordItem(String title, String id, String passwd, String memo) {
        this.title = title;
        this.id = id;
        this.passwd = passwd;
        this.memo = memo;
    }

    @NonNull
    @Override
    public String toString() {
        return "[ " + title + " ]\nid: " + id + "\npw: " + passwd + "\n--- Memo ---\n" + memo;
    }
}
