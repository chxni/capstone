package com.cookandroid.zzikmuck_material.MaterialListDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MaterialList {
    @PrimaryKey(autoGenerate = true)  //id 자동 관리
    private int id = 0;

    public String mName;

    public boolean sOnOff;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean getsOnOff() {
        return sOnOff;
    }

    public void setsOnOff(boolean sOnOff) {
        this.sOnOff = sOnOff;
    }
}
