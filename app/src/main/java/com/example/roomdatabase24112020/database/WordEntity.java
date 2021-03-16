package com.example.roomdatabase24112020.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class WordEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    private String en;

    private String vn;

    private boolean isMemorized;

    public WordEntity(long id, String en, String vn, boolean isMemorized) {
        this.id = id;
        this.en = en;
        this.vn = vn;
        this.isMemorized = isMemorized;
    }

    @Ignore
    public WordEntity(String en, String vn, boolean isMemorized) {
        this.en = en;
        this.vn = vn;
        this.isMemorized = isMemorized;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public boolean isMemorized() {
        return isMemorized;
    }

    public void setMemorized(boolean memorized) {
        isMemorized = memorized;
    }
}
