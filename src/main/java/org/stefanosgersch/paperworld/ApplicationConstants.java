package org.stefanosgersch.paperworld;

/**
 * This class contains constants and other information about our
 * data, the application, etc
 */
public class ApplicationConstants {

    public static final String YEAR = "year";
    public static final String TITLE = "title";
    public static final String ABSTRACT = "abstract";
    public static final String FULL_TEXT = "full_text";

    // if I do the join between papers and authors
    public static final String AUTHORS = "full_name";


    // lucene specific
    // changed project due to issues
    public static final String INDEX_PATH = "C:\\Users\\STEVE\\Desktop\\searchEngine\\paperWorld\\src\\main\\resources\\index";
    public static final String CORPUS_PATH = "C:\\Users\\STEVE\\Desktop\\searchEngine\\paperWorld\\src\\main\\resources\\corpus";

    public static final int MAX_SEARCH = 100;

    // UI specific
    public static final String WINDOW_TITLE = "paperWorld";
}