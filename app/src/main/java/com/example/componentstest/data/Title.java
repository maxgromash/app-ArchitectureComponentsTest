package com.example.componentstest.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "titles")
public class Title {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;

    public Title(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
