package com.oracle.oda.ext.pojos;

import java.util.Collections;
import java.util.List;

public class OrderReport {
    private String cat;
    private int count = 0;
    private List<OrderReport> items = Collections.emptyList();

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<OrderReport> getItems() {
        return items;
    }

    public void setItems(List<OrderReport> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderReport [cat=" + cat + ", count=" + count + ", items=" + (items == null ? 0 : items.size()) + "]";
    }

}
