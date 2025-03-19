package org.stefanosgersch.paperworld;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private final IndexSearcher indexSearcher;

    public Searcher(String indexDirectory) throws IOException {
        Directory index = FSDirectory.open(new File(indexDirectory).toPath());
        indexSearcher = new IndexSearcher(DirectoryReader.open(index));
    }

    public List<Results> search(String userInputQuery, SearchOptions searchOptions) throws IOException, ParseException {

        Query query = QueryBuilder.buildQuery(userInputQuery);
        TopDocs hits;

        // does not work
        if (searchOptions.getSort().equals("year")) {
            SortField sortField = new SortField("year", SortField.Type.INT, true);  // descending order
            Sort sort = new Sort(sortField);
            hits = indexSearcher.search(query, ApplicationConstants.MAX_SEARCH, sort);
        } else {
            hits = indexSearcher.search(query, ApplicationConstants.MAX_SEARCH, Sort.RELEVANCE);
        }

        List<Results> results = new ArrayList<>();
        StoredFields storedFields = indexSearcher.storedFields();
        for (ScoreDoc hit: hits.scoreDocs) {
            Document document = storedFields.document(hit.doc);
            results.add(new Results(document.get(ApplicationConstants.YEAR),
                    document.get(ApplicationConstants.TITLE),
                    document.get(ApplicationConstants.ABSTRACT),
                    document.get(ApplicationConstants.FULL_TEXT),
                    document.get(ApplicationConstants.AUTHORS)));

        }

        return results;
    }
}
