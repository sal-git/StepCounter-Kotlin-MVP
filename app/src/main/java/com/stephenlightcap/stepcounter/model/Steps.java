package com.stephenlightcap.stepcounter.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Germex on 5/20/2017.
 */

public class Steps extends RealmObject {
    @PrimaryKey
    private long id;
    private Date date;
    private int count;

    public Steps() {
    }

    public Steps(long id, Date date, int count) {
        this.id = id;
        this.date = date;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
