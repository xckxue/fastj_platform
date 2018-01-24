package com.upotv.mcs.role.entity;

/**
 * Created by wow on 2017/8/18.
 */
public class MenuPriv {
    private String id;
    private String name;
    private boolean checked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
