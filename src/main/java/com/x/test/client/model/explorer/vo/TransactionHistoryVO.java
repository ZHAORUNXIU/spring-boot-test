package com.x.test.client.model.explorer.vo;

import java.io.Serializable;

public class TransactionHistoryVO implements Serializable {

    private static final long serialVersionUID = 8718060005385905856L;

    private String date;
    private int count;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}