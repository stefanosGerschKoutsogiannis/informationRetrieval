package org.stefanosgersch.paperworld;

public class SearchOptions {

    private String sort;

    public SearchOptions() {
        this.sort = "relevance";
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
