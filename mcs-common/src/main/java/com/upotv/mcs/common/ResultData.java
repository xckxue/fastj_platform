package com.upotv.mcs.common;

/**
 * Created by wow on 2017/3/21.
 */
public class ResultData {
    // 返回数据
    private Object rows;

    private long total;

    /**
     * 分页使用
     * @param rows
     * @param total
     */
    public ResultData(Object rows, long total) {
        this.total = total;
        this.rows = rows;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
