package com.cookandroid.zzikmuck_material.MaterialListDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MaterialListDAO {
    @Insert
    void setInsertMatList(MaterialList foodMat);

    @Update
    void setUpdateMatList(MaterialList foodMat);

    @Delete
    void setDeleteMatList(MaterialList foodMat);

    @Query("SELECT * FROM MaterialList")
    List<MaterialList> getAll();
}
