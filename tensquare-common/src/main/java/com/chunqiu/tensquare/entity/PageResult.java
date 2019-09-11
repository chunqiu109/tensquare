package com.chunqiu.tensquare.entity;


import java.util.List;

/**
 *功能描述  返回值分页类
 * @author Wangchunqiu
 * @date $
 */
public class PageResult<T> {

    private Long total;//总记录数
    private List<T> rows;//当前页的数据列表

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
