package com.meyok.c5_filminterface.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result<T> {
    int code;
    @SerializedName("list")
    List<T> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
