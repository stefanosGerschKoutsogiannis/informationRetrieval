package org.stefanosgersch.paperworld;

/**
 * This class is responsible for creating Results objects for every retrieved Document
 */
public class Results {

    private final String year;
    private final String title;
    private final String abstractText;
    private final String fullText;
    private final String authors;

    public Results(String year, String title, String abstractText, String fullText, String authors) {
        this.year = year;
        this.title = title;
        this.abstractText = abstractText;
        this.fullText = fullText;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public String getFullText() {
        return fullText;
    }

    public String getAuthors() {
        return authors;
    }
}
