package com.upotv.mcs.role.entity;

import com.upotv.mcs.main.entity.Mcs_menu;
import org.spider.util.StringUtils;

import java.util.List;


public class TreeData {
    private String id;
    private String text;
    private String iconCls;
    private boolean checked;
    private String state = "closed";
    private TreeAttribute attributes;
    private List<TreeData> children;

    public static TreeData parse(Mcs_menu menu) {
        TreeData treeData = new TreeData();
        treeData.setId(menu.getMenuId()+"");
        treeData.setIconCls(menu.getIcon());
        treeData.setText(menu.getName());
        if (!StringUtils.isNullOrEmpty(menu.getPath())) {
            TreeAttribute attribute = new TreeData().new TreeAttribute();
            attribute.setUrl(menu.getPath());
            treeData.setAttributes(attribute);
        }
        return treeData;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public TreeAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(TreeAttribute attributes) {
        this.attributes = attributes;
    }

    public List<TreeData> getChildren() {
        return children;
    }

    public void setChildren(List<TreeData> children) {
        this.children = children;
    }

    public class TreeAttribute {
        private String url ="";

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
