package com.upotv.mcs.dict.entity;

/**
 * Created by dhy on 2017-8-2.
 */
public class McsCodeSelectVo {
    private String codeType;
    private int rows;
    private int page;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
}
