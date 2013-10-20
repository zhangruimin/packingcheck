package com.lvtuxiongdi.packingcheck.model;

public class Packing extends BaseModel {

    private String name;
    private int drawable;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDrawable() {
        return drawable;
    }
    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
    public Packing(long id, String name, int drawable) {
        this.id = id;
        this.name = name;
        this.drawable = drawable;
    }

    public Packing() {
    }
}
