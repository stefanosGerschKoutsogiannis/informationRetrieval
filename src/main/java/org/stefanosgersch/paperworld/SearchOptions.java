package org.stefanosgersch.paperworld;

/**
 * This class is responsible for the setting of search options
 * Does not work
 */
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
