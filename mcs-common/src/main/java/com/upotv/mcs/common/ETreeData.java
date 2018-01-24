package com.upotv.mcs.common;

import java.util.List;

/**
 * Created by wow on 2017/8/21.
 */
public class ETreeData {
    private Integer id;
    private String text;
    private String iconCls;
    private String state = "closed";
    private ETreeAttribute attributes;
    private List<ETreeData> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ETreeAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(ETreeAttribute attributes) {
        this.attributes = attributes;
    }

    public List<ETreeData> getChildren() {
        return children;
    }

    public void setChildren(List<ETreeData> children) {
        this.children = children;
    }
}
