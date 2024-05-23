package org.stefanosgersch.paperworld;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the searching of documents in the corpus
 */
public class Searcher {

    // our IndexSearcher instance, we create and use only one through
    // the entire use of the app
    private final IndexSearcher indexSearcher;

    public Searcher(String indexDirectory) throws IOException {

        // open the already created index
        Directory index = FSDirectory.open(new File(indexDirectory).toPath());

        // create the IndexSearcher instance and open the index for read-only
        // with DirectoryReader
        indexSearcher = new IndexSearcher(DirectoryReader.open(index));
    }

    // this method creates the query via QueryBuilder, Lucene search returns TopDocs and then we make objects of them to use them
    public List<Results> search(String userInputQuery, SearchOptions searchOptions) throws IOException, ParseException {

        // takes the user input query and translate it to a query lucene can understand
        Query query = QueryBuilder.buildQuery(userInputQuery);

        // in the TopDocs object, are stored references of the top MAX_SEARCH most relevant hits of the query
        TopDocs topDocs;

        // does not work
        if (searchOptions.getSort().equals("year")) {
            SortField sortField = new SortField("year", SortField.Type.INT, true);  // descending order
            Sort sort = new Sort(sortField);
            topDocs = indexSearcher.search(query, ApplicationConstants.MAX_SEARCH, sort);
        } else {
            topDocs = indexSearcher.search(query, ApplicationConstants.MAX_SEARCH, Sort.RELEVANCE);
        }

        // creates list to store the results the user will see in the UI
        List<Results> results = new ArrayList<>();

        // for each of the references stored in the topDocs
        for (ScoreDoc scoreDoc: topDocs.scoreDocs) {

            // create document and set it to the scoreDocs document
            // deprecated, be cautious
            Document document = indexSearcher.doc(scoreDoc.doc);

            // create and add Results object to the list the user will see
            // is the error of paperViewer here?
            results.add(new Results(document.get(ApplicationConstants.YEAR),
                    document.get(ApplicationConstants.TITLE),
                    document.get(ApplicationConstants.ABSTRACT),
                    document.get(ApplicationConstants.FULL_TEXT),
                    document.get(ApplicationConstants.AUTHORS)));

        }

        return results;
    }
}
