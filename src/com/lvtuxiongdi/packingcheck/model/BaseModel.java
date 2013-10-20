package com.lvtuxiongdi.packingcheck.model;

import java.io.Serializable;

public class BaseModel implements Serializable {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
