package com.oracle.oda.ext.pojos;

public class ProdStat {
    private String name;
    private int count = 0;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProdStat [name=" + name + ", count=" + count + "]";
    }

}
